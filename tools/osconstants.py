#!/usr/bin/env python3
"""Writes runtime/framework/android/system/OsConstants.java: the values of 32-bit ARM Android.

Values come from the C headers, as AOSP's libcore reads them: the host's Linux headers (compiled with gcc), with what
differs on Android taken from bionic (commit BIONIC below): <bits/sysconf.h> (_SC_*), <netdb.h> (EAI_*, AI_*, NI_*),
the kernel's ARM <asm/fcntl.h> (O_DIRECTORY, O_NOFOLLOW, O_DIRECT, O_LARGEFILE), 32-bit F_*LK64, and the real-time
signals bionic reserves. Usage: tools/osconstants.py ANDROID_JAR (needs gcc, javap and network access).
"""
import base64, os, re, subprocess, sys, tempfile, urllib.request

BIONIC = '731631f300090436d7f5df80d50b6275c8c60a93'
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
work = tempfile.mkdtemp()
d = work + '/'

def fetch(path):
    url = 'https://android.googlesource.com/platform/bionic/+/%s/%s?format=TEXT' % (BIONIC, path)
    with urllib.request.urlopen(url) as r: open(d + os.path.basename(path), 'wb').write(base64.b64decode(r.read()))

for p in ('libc/include/bits/sysconf.h', 'libc/include/netdb.h'): fetch(p)
jar = sys.argv[1]
out = subprocess.run(['javap', '-cp', jar, 'android.system.OsConstants'], capture_output=True, text=True).stdout
open(d + 'all.txt', 'w').write('\n'.join(sorted(re.findall(r'static final int (\w+);', out))) + '\n')

HEADERS = """#define _GNU_SOURCE
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>
#include <poll.h>
#include <netdb.h>
#include <sys/socket.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/statvfs.h>
#include <sys/wait.h>
#include <sys/prctl.h>
#include <sys/ioctl.h>
#include <net/if.h>
#include <net/if_arp.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <netinet/udp.h>
#include <netinet/icmp6.h>
#include <netinet/ip_icmp.h>
#include <linux/capability.h>
#include <linux/if_addr.h>
#include <linux/rtnetlink.h>
#include <linux/netlink.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h>
#include <linux/vm_sockets.h>
#include <stdlib.h>
#ifndef UDP_GRO
#define UDP_GRO 104
#endif
#ifndef UDP_SEGMENT
#define UDP_SEGMENT 103
#endif
int main(void) {
"""
METHODS0 = set('S_ISBLK S_ISCHR S_ISDIR S_ISFIFO S_ISLNK S_ISREG S_ISSOCK WCOREDUMP WEXITSTATUS WIFEXITED WIFSIGNALED WIFSTOPPED WSTOPSIG WTERMSIG'.split())
cnames = [l.strip() for l in open(d + 'all.txt') if l.strip() and l.strip() not in METHODS0 and not re.match(r'^(_SC_|EAI_|AI_|NI_)', l)]
c = HEADERS + ''.join('printf("%s %%ld\\n", (long)(%s));\n' % (n, n) for n in cnames) + 'return 0; }\n'
open(d + 'oc.c', 'w').write(c)
subprocess.run(['gcc', '-w', '-o', d + 'oc', d + 'oc.c'], check=True)
open(d + 'vals.txt', 'w').write(subprocess.run([d + 'oc'], capture_output=True, text=True, check=True).stdout)
METHODS = set('S_ISBLK S_ISCHR S_ISDIR S_ISFIFO S_ISLNK S_ISREG S_ISSOCK WCOREDUMP WEXITSTATUS WIFEXITED WIFSIGNALED WIFSTOPPED WSTOPSIG WTERMSIG'.split())
names = [l.strip() for l in open(d + 'all.txt') if l.strip() and l.strip() not in METHODS]
vals = {}
for l in open(d + 'vals.txt'):
    k, v = l.split()
    if k.startswith('='): vals[k[1:]] = int(v)
    elif v != '?' and k not in vals: vals[k] = int(v)
# bionic values (bionic/libc/include/bits/sysconf.h, netdb.h; kernel uapi asm-arm fcntl.h; reserved_signals.h)
for m in re.finditer(r'#define (_SC_[A-Z0-9_]+) +(0x[0-9a-fA-F]+)', open(d + 'sysconf.h').read()): vals[m.group(1)] = int(m.group(2), 16)
for m in re.finditer(r'#define\s+((?:EAI|AI|NI)_[A-Z0-9_]+)\s+(0x[0-9a-fA-F]+|\d+)', open(d + 'netdb.h').read()): vals[m.group(1)] = int(m.group(2), 0)
vals.update(O_DIRECTORY=0o40000, O_NOFOLLOW=0o100000, O_DIRECT=0o200000, O_LARGEFILE=0o400000,
            F_GETLK64=12, F_SETLK64=13, F_SETLKW64=14, SIGRTMIN=32 + 10, SIGRTMAX=64)
miss = [n for n in names if n not in vals]
if miss: sys.exit('missing: %s' % miss)
groups = {}
for n in names:
    g = n.split('_')[0] if not n.startswith('_SC') else '_SC'
    if re.match(r'^E[A-Z0-9]+$', n): g = 'E'
    groups.setdefault(g, []).append(n)
out = []
for g in sorted(groups):
    items = ['%s = %s' % (n, hex(vals[n]) if vals[n] > 255 else vals[n]) for n in sorted(groups[g])]
    lines, cur = [], '  public static final int '
    for i, it in enumerate(items):
        piece = it + (', ' if i < len(items) - 1 else ';')
        if len(cur) + len(piece) > 118: lines.append(cur.rstrip()); cur = '      '
        cur += piece
    lines.append(cur)
    out.append('\n'.join(lines))
open(d + 'fields.txt', 'w').write('\n'.join(out) + '\n')
# errno names and messages from the host (Linux; same numbers and strerror texts as bionic)
import errno, os
en = sorted((vals[n], n) for n in names if n.startswith('E') and n[1:2].isupper() and not n.startswith('EAI_') and not n.startswith('ETH_') and n not in ('EXIT_FAILURE', 'EXIT_SUCCESS'))
seen = set(); cases = []
for v, n in en:
    if v in seen: continue
    seen.add(v)
    pref = {11: 'EAGAIN', 95: 'EOPNOTSUPP', 35: 'EDEADLK'}.get(v, n)
    cases.append('      case %d: return "%s";' % (v, pref))
open(d + 'errno.txt', 'w').write('\n'.join(cases) + '\n')
gai = sorted((vals[n], n) for n in names if n.startswith('EAI_'))
open(d + 'gai.txt', 'w').write('\n'.join('      case %d: return "%s";' % g for g in gai) + '\n')
print(len(names), 'fields')
import os as _os
msgs = []
seen = set()
for v, n in en:
    if v in seen: continue
    seen.add(v)
    msgs.append('      case %d: return "%s";' % (v, _os.strerror(v).replace('"', '\\"')))
gaimsg = {1: "Address family for hostname not supported", 2: "Temporary failure in name resolution", 3: "Invalid value for ai_flags",
  4: "Non-recoverable failure in name resolution", 5: "ai_family not supported", 6: "Memory allocation failure",
  7: "No address associated with hostname", 8: "hostname nor servname provided, or not known", 9: "servname not supported for ai_socktype",
  10: "ai_socktype not supported", 11: "System error returned in errno", 12: "Invalid value for hints", 13: "Resolved protocol is unknown",
  14: "Argument buffer overflow"}
java = '''package android.system;

/**
 * android.system.OsConstants: the values of 32-bit ARM Android (bionic, Linux uapi), as AOSP's libcore reads them from
 * the C headers. Generated by tools/osconstants.py.
 */
public final class OsConstants {
  private OsConstants() {}
%s
  public static boolean S_ISBLK(int mode) { return (mode & S_IFMT) == S_IFBLK; }
  public static boolean S_ISCHR(int mode) { return (mode & S_IFMT) == S_IFCHR; }
  public static boolean S_ISDIR(int mode) { return (mode & S_IFMT) == S_IFDIR; }
  public static boolean S_ISFIFO(int mode) { return (mode & S_IFMT) == S_IFIFO; }
  public static boolean S_ISREG(int mode) { return (mode & S_IFMT) == S_IFREG; }
  public static boolean S_ISLNK(int mode) { return (mode & S_IFMT) == S_IFLNK; }
  public static boolean S_ISSOCK(int mode) { return (mode & S_IFMT) == S_IFSOCK; }
  public static int WEXITSTATUS(int status) { return (status & 0xff00) >> 8; }
  public static boolean WCOREDUMP(int status) { return (status & 0x80) != 0; }
  public static int WTERMSIG(int status) { return status & 0x7f; }
  public static int WSTOPSIG(int status) { return WEXITSTATUS(status); }
  public static boolean WIFEXITED(int status) { return WTERMSIG(status) == 0; }
  public static boolean WIFSTOPPED(int status) { return WTERMSIG(status) == 0x7f; }
  public static boolean WIFSIGNALED(int status) { return WTERMSIG(status + 1) >= 2; }

  /** the name of an errno value ("ENOENT"), or null */
  public static String errnoName(int errno) {
    switch (errno) {
%s
      default: return null;
    }
  }
  /** the name of a getaddrinfo error ("EAI_NONAME"), or null */
  public static String gaiName(int error) {
    switch (error) {
%s
      default: return null;
    }
  }
  /** strerror(3) */
  static String strerror(int errno) {
    switch (errno) {
%s
      default: return "Unknown error " + errno;
    }
  }
  /** gai_strerror(3) */
  static String gaiStrerror(int error) {
    switch (error) {
      case 0: return "Success";
%s
      default: return "Unknown error";
    }
  }
}
''' % ('\n'.join(out), '\n'.join(cases), open(d + 'gai.txt').read().rstrip('\n'), '\n'.join(msgs),
       '\n'.join('      case %d: return "%s";' % kv for kv in sorted(gaimsg.items())))
open(os.path.join(ROOT, 'runtime/framework/android/system/OsConstants.java'), 'w').write(java)
