package a2w.xml;

import a2w.xml.dom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** The DOM implementation behind javax.xml.parsers.DocumentBuilder (a plain tree; no validation, no live entity nodes). */
public final class Dom {
  private Dom() {}

  static DOMException err(short code, String m) { return new DOMException(code, m); }

  static final class Children implements NodeList {
    final List<Node> list;
    Children(List<Node> l) { list = l; }
    @Override public Node item(int i) { return i >= 0 && i < list.size() ? list.get(i) : null; }
    @Override public int getLength() { return list.size(); }
  }
  static final NodeList EMPTY = new Children(new ArrayList<>());

  public abstract static class NodeImpl implements Node {
    DocumentImpl doc;
    ParentImpl parent;
    HashMap<String, Object[]> userData;
    NodeImpl(DocumentImpl d) { doc = d; }
    @Override public String getNodeValue() { return null; }
    @Override public void setNodeValue(String v) {}
    @Override public Node getParentNode() { return parent; }
    @Override public NodeList getChildNodes() { return EMPTY; }
    @Override public Node getFirstChild() { return null; }
    @Override public Node getLastChild() { return null; }
    int index() { return parent == null ? -1 : parent.kids.indexOf(this); }
    @Override public Node getPreviousSibling() { int i = index(); return i > 0 ? parent.kids.get(i - 1) : null; }
    @Override public Node getNextSibling() { int i = index(); return i >= 0 && i + 1 < parent.kids.size() ? parent.kids.get(i + 1) : null; }
    @Override public NamedNodeMap getAttributes() { return null; }
    @Override public Document getOwnerDocument() { return doc; }
    @Override public Node insertBefore(Node n, Node ref) { throw err(DOMException.HIERARCHY_REQUEST_ERR, "no children here"); }
    @Override public Node replaceChild(Node n, Node old) { throw err(DOMException.HIERARCHY_REQUEST_ERR, "no children here"); }
    @Override public Node removeChild(Node old) { throw err(DOMException.NOT_FOUND_ERR, "no children here"); }
    @Override public Node appendChild(Node n) { throw err(DOMException.HIERARCHY_REQUEST_ERR, "no children here"); }
    @Override public boolean hasChildNodes() { return false; }
    @Override public void normalize() {}
    @Override public boolean isSupported(String f, String v) { return doc != null && doc.getImplementation().hasFeature(f, v); }
    @Override public String getNamespaceURI() { return null; }
    @Override public String getPrefix() { return null; }
    @Override public void setPrefix(String p) {}
    @Override public String getLocalName() { return null; }
    @Override public boolean hasAttributes() { return false; }
    @Override public String getBaseURI() { return doc == null ? null : doc.getDocumentURI(); }
    @Override public short compareDocumentPosition(Node other) {
      if (other == this) return 0;
      List<Node> a = ancestry(this), b = ancestry(other);
      if (a.get(0) != b.get(0)) return DOCUMENT_POSITION_DISCONNECTED | DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC | DOCUMENT_POSITION_PRECEDING;
      int i = 0;
      while (i < a.size() && i < b.size() && a.get(i) == b.get(i)) i++;
      if (i == a.size()) return DOCUMENT_POSITION_CONTAINED_BY | DOCUMENT_POSITION_FOLLOWING;
      if (i == b.size()) return DOCUMENT_POSITION_CONTAINS | DOCUMENT_POSITION_PRECEDING;
      Node p = a.get(i - 1);
      NodeList kids = p.getChildNodes();
      for (int k = 0; k < kids.getLength(); k++) {
        if (kids.item(k) == a.get(i)) return DOCUMENT_POSITION_FOLLOWING;
        if (kids.item(k) == b.get(i)) return DOCUMENT_POSITION_PRECEDING;
      }
      return DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC;
    }
    static List<Node> ancestry(Node n) {
      ArrayList<Node> l = new ArrayList<>();
      for (Node x = n; x != null; x = x instanceof Attr ? ((Attr) x).getOwnerElement() : x.getParentNode()) l.add(0, x);
      return l;
    }
    @Override public String getTextContent() { return getNodeValue(); }
    @Override public void setTextContent(String t) { setNodeValue(t); }
    @Override public boolean isSameNode(Node other) { return other == this; }
    @Override public String lookupPrefix(String uri) {
      if (uri == null) return null;
      for (Node n = this; n != null; n = n.getParentNode()) {
        if (n instanceof ElementImpl) {
          ElementImpl e = (ElementImpl) n;
          if (uri.equals(e.ns) && e.prefix != null) return e.prefix;
          for (AttrImpl a : e.attrs.list) if ("xmlns".equals(a.prefix) && uri.equals(a.value)) return a.local;
        }
      }
      return null;
    }
    @Override public boolean isDefaultNamespace(String uri) { String d = lookupNamespaceURI(null); return d == null ? uri == null : d.equals(uri); }
    @Override public String lookupNamespaceURI(String prefix) {
      for (Node n = this instanceof AttrImpl ? ((AttrImpl) this).owner : this; n != null; n = n.getParentNode()) {
        if (n instanceof ElementImpl) {
          ElementImpl e = (ElementImpl) n;
          if (e.ns != null && (prefix == null ? e.prefix == null : prefix.equals(e.prefix))) return e.ns;
          for (AttrImpl a : e.attrs.list) {
            if (prefix == null && a.prefix == null && "xmlns".equals(a.name)) return a.value.isEmpty() ? null : a.value;
            if (prefix != null && "xmlns".equals(a.prefix) && prefix.equals(a.local)) return a.value.isEmpty() ? null : a.value;
          }
        }
      }
      return null;
    }
    @Override public boolean isEqualNode(Node o) {
      if (o == null || o.getNodeType() != getNodeType()) return false;
      if (!eq(getNodeName(), o.getNodeName()) || !eq(getLocalName(), o.getLocalName()) || !eq(getNamespaceURI(), o.getNamespaceURI())
          || !eq(getPrefix(), o.getPrefix()) || !eq(getNodeValue(), o.getNodeValue())) return false;
      NamedNodeMap a = getAttributes(), b = o.getAttributes();
      if ((a == null) != (b == null)) return false;
      if (a != null) {
        if (a.getLength() != b.getLength()) return false;
        for (int i = 0; i < a.getLength(); i++) {
          Node x = a.item(i);
          Node y = x.getLocalName() != null ? b.getNamedItemNS(x.getNamespaceURI(), x.getLocalName()) : b.getNamedItem(x.getNodeName());
          if (y == null || !x.isEqualNode(y)) return false;
        }
      }
      NodeList c = getChildNodes(), d = o.getChildNodes();
      if (c.getLength() != d.getLength()) return false;
      for (int i = 0; i < c.getLength(); i++) if (!c.item(i).isEqualNode(d.item(i))) return false;
      return true;
    }
    static boolean eq(Object a, Object b) { return a == null ? b == null : a.equals(b); }
    @Override public Object getFeature(String f, String v) { return isSupported(f, v) ? this : null; }
    @Override public Object setUserData(String key, Object data, UserDataHandler h) {
      if (userData == null) userData = new HashMap<>();
      Object[] old = data == null ? userData.remove(key) : userData.put(key, new Object[] {data, h});
      return old == null ? null : old[0];
    }
    @Override public Object getUserData(String key) { Object[] v = userData == null ? null : userData.get(key); return v == null ? null : v[0]; }
    void notifyData(short op, Node dst) {
      if (userData == null) return;
      for (java.util.Map.Entry<String, Object[]> e : userData.entrySet())
        if (e.getValue()[1] != null) ((UserDataHandler) e.getValue()[1]).handle(op, e.getKey(), e.getValue()[0], this, dst);
    }
    abstract NodeImpl copy(DocumentImpl to, boolean deep);
    @Override public Node cloneNode(boolean deep) { NodeImpl c = copy(doc, deep); notifyData(UserDataHandler.NODE_CLONED, c); return c; }
    void setDoc(DocumentImpl d) { doc = d; }
    @Override public String toString() { return "[" + getNodeName() + ": " + getNodeValue() + "]"; }
  }

  public abstract static class ParentImpl extends NodeImpl {
    final ArrayList<Node> kids = new ArrayList<>();
    ParentImpl(DocumentImpl d) { super(d); }
    @Override public NodeList getChildNodes() { return new Children(kids); }
    @Override public Node getFirstChild() { return kids.isEmpty() ? null : kids.get(0); }
    @Override public Node getLastChild() { return kids.isEmpty() ? null : kids.get(kids.size() - 1); }
    @Override public boolean hasChildNodes() { return !kids.isEmpty(); }
    boolean accepts(Node n) { short t = n.getNodeType(); return t != Node.ATTRIBUTE_NODE && t != Node.DOCUMENT_NODE && t != Node.DOCUMENT_TYPE_NODE; }
    @Override public Node insertBefore(Node n, Node ref) {
      if (n instanceof DocumentFragment) {
        for (Node k : new ArrayList<>(((ParentImpl) n).kids)) insertBefore(k, ref);
        return n;
      }
      if (!(n instanceof NodeImpl)) throw err(DOMException.WRONG_DOCUMENT_ERR, "foreign node");
      NodeImpl c = (NodeImpl) n;
      if (c.doc != doc && !(this instanceof DocumentImpl && c.doc == this)) throw err(DOMException.WRONG_DOCUMENT_ERR, "node belongs to another document");
      if (!accepts(n)) throw err(DOMException.HIERARCHY_REQUEST_ERR, "cannot insert " + n.getNodeName() + " into " + getNodeName());
      for (Node p = this; p != null; p = p.getParentNode()) if (p == n) throw err(DOMException.HIERARCHY_REQUEST_ERR, "would be its own ancestor");
      int at = kids.size();
      if (ref != null) { at = kids.indexOf(ref); if (at < 0) throw err(DOMException.NOT_FOUND_ERR, "reference node is not a child"); }
      if (c.parent != null) { int old = c.parent.kids.indexOf(c); if (c.parent == this && old < at) at--; c.parent.kids.remove(c); }
      kids.add(at, c);
      c.parent = this;
      return n;
    }
    @Override public Node appendChild(Node n) { return insertBefore(n, null); }
    @Override public Node replaceChild(Node n, Node old) {
      if (kids.indexOf(old) < 0) throw err(DOMException.NOT_FOUND_ERR, "not a child");
      if (n == old) return old;
      insertBefore(n, old);
      removeChild(old);
      return old;
    }
    @Override public Node removeChild(Node old) {
      if (!kids.remove(old)) throw err(DOMException.NOT_FOUND_ERR, "not a child");
      ((NodeImpl) old).parent = null;
      return old;
    }
    @Override public void normalize() {
      for (int i = 0; i < kids.size(); i++) {
        Node k = kids.get(i);
        if (k.getNodeType() == Node.TEXT_NODE) {
          TextImpl t = (TextImpl) k;
          while (i + 1 < kids.size() && kids.get(i + 1).getNodeType() == Node.TEXT_NODE) { t.data += ((TextImpl) kids.get(i + 1)).data; removeChild(kids.get(i + 1)); }
          if (t.data.isEmpty()) { removeChild(t); i--; }
        } else k.normalize();
      }
    }
    @Override public String getTextContent() {
      StringBuilder sb = new StringBuilder();
      text(this, sb);
      return sb.toString();
    }
    static void text(Node n, StringBuilder sb) {
      for (Node k = n.getFirstChild(); k != null; k = k.getNextSibling()) {
        short t = k.getNodeType();
        if (t == Node.TEXT_NODE || t == Node.CDATA_SECTION_NODE) sb.append(k.getNodeValue());
        else if (t == Node.ELEMENT_NODE || t == Node.ENTITY_REFERENCE_NODE || t == Node.DOCUMENT_FRAGMENT_NODE) text(k, sb);
      }
    }
    @Override public void setTextContent(String s) {
      for (Node k : new ArrayList<>(kids)) removeChild(k);
      if (s != null && !s.isEmpty()) appendChild(doc().createTextNode(s));
    }
    DocumentImpl doc() { return this instanceof DocumentImpl ? (DocumentImpl) this : doc; }
    void copyKids(ParentImpl to, DocumentImpl d) { for (Node k : kids) to.appendChild(((NodeImpl) k).copy(d, true)); }
    @Override void setDoc(DocumentImpl d) { super.setDoc(d); for (Node k : kids) ((NodeImpl) k).setDoc(d); }
    void collect(String ns, String local, boolean useNs, List<Node> out) {
      for (Node k : kids) {
        if (!(k instanceof ElementImpl)) continue;
        ElementImpl e = (ElementImpl) k;
        boolean m;
        if (useNs) m = ("*".equals(ns) || eq(ns == null || ns.isEmpty() ? null : ns, e.ns)) && ("*".equals(local) || local.equals(e.local != null ? e.local : e.name));
        else m = "*".equals(local) || local.equals(e.name);
        if (m) out.add(e);
        e.collect(ns, local, useNs, out);
      }
    }
  }

  public static final class DocumentImpl extends ParentImpl implements Document {
    String uri, inputEncoding, xmlEncoding, version = "1.0";
    boolean standalone, strict = true;
    final Impl impl;
    public DocumentImpl(Impl impl) { super(null); this.impl = impl; }
    @Override public String getNodeName() { return "#document"; }
    @Override public short getNodeType() { return DOCUMENT_NODE; }
    @Override public Document getOwnerDocument() { return null; }
    @Override boolean accepts(Node n) {
      short t = n.getNodeType();
      if (t == ELEMENT_NODE) return getDocumentElement() == null;
      if (t == DOCUMENT_TYPE_NODE) return getDoctype() == null;
      return t == COMMENT_NODE || t == PROCESSING_INSTRUCTION_NODE;
    }
    @Override public Node insertBefore(Node n, Node ref) {
      if (n instanceof NodeImpl && ((NodeImpl) n).doc == null && n instanceof DocumentType) ((NodeImpl) n).doc = this;
      return super.insertBefore(n, ref);
    }
    @Override public String getTextContent() { return null; }
    @Override public void setTextContent(String s) {}
    @Override public DocumentType getDoctype() { for (Node k : kids) if (k instanceof DocumentType) return (DocumentType) k; return null; }
    @Override public DOMImplementation getImplementation() { return impl; }
    @Override public Element getDocumentElement() { for (Node k : kids) if (k instanceof Element) return (Element) k; return null; }
    @Override public Element createElement(String tag) { checkName(tag); return new ElementImpl(this, null, tag); }
    @Override public DocumentFragment createDocumentFragment() { return new FragmentImpl(this); }
    @Override public Text createTextNode(String data) { return new TextImpl(this, data); }
    @Override public Comment createComment(String data) { return new CommentImpl(this, data); }
    @Override public CDATASection createCDATASection(String data) { return new CDataImpl(this, data); }
    @Override public ProcessingInstruction createProcessingInstruction(String target, String data) { checkName(target); return new PIImpl(this, target, data); }
    @Override public Attr createAttribute(String name) { checkName(name); return new AttrImpl(this, null, name); }
    @Override public EntityReference createEntityReference(String name) { checkName(name); return new EntityRefImpl(this, name); }
    @Override public NodeList getElementsByTagName(String tag) { ArrayList<Node> l = new ArrayList<>(); collect(null, tag, false, l); return new Children(l); }
    @Override public Node importNode(Node n, boolean deep) {
      NodeImpl c = importCopy(n, deep);
      if (n instanceof NodeImpl) ((NodeImpl) n).notifyData(UserDataHandler.NODE_IMPORTED, c);
      return c;
    }
    NodeImpl importCopy(Node n, boolean deep) {
      if (n instanceof NodeImpl) return ((NodeImpl) n).copy(this, deep);
      switch (n.getNodeType()) {
        case ELEMENT_NODE: {
          ElementImpl e = new ElementImpl(this, n.getNamespaceURI(), n.getNodeName());
          NamedNodeMap at = n.getAttributes();
          for (int i = 0; at != null && i < at.getLength(); i++) e.setAttributeNodeNS((Attr) importCopy(at.item(i), true));
          if (deep) for (Node k = n.getFirstChild(); k != null; k = k.getNextSibling()) e.appendChild(importCopy(k, true));
          return e;
        }
        case ATTRIBUTE_NODE: { AttrImpl a = new AttrImpl(this, n.getNamespaceURI(), n.getNodeName()); a.value = n.getNodeValue(); return a; }
        case TEXT_NODE: return new TextImpl(this, n.getNodeValue());
        case CDATA_SECTION_NODE: return new CDataImpl(this, n.getNodeValue());
        case COMMENT_NODE: return new CommentImpl(this, n.getNodeValue());
        case PROCESSING_INSTRUCTION_NODE: return new PIImpl(this, n.getNodeName(), n.getNodeValue());
        case ENTITY_REFERENCE_NODE: return new EntityRefImpl(this, n.getNodeName());
        case DOCUMENT_FRAGMENT_NODE: {
          FragmentImpl f = new FragmentImpl(this);
          if (deep) for (Node k = n.getFirstChild(); k != null; k = k.getNextSibling()) f.appendChild(importCopy(k, true));
          return f;
        }
        default: throw err(DOMException.NOT_SUPPORTED_ERR, "cannot import " + n.getNodeName());
      }
    }
    @Override public Element createElementNS(String ns, String qname) { checkName(qname); return new ElementImpl(this, ns == null || ns.isEmpty() ? null : ns, qname); }
    @Override public Attr createAttributeNS(String ns, String qname) { checkName(qname); return new AttrImpl(this, ns == null || ns.isEmpty() ? null : ns, qname); }
    @Override public NodeList getElementsByTagNameNS(String ns, String local) { ArrayList<Node> l = new ArrayList<>(); collect(ns, local, true, l); return new Children(l); }
    @Override public Element getElementById(String id) { return byId(this, id); }
    static Element byId(Node n, String id) {
      for (Node k = n.getFirstChild(); k != null; k = k.getNextSibling()) {
        if (!(k instanceof ElementImpl)) continue;
        ElementImpl e = (ElementImpl) k;
        for (AttrImpl a : e.attrs.list) if (a.id && id.equals(a.value)) return e;
        Element f = byId(e, id);
        if (f != null) return f;
      }
      return null;
    }
    @Override public String getInputEncoding() { return inputEncoding; }
    @Override public String getXmlEncoding() { return xmlEncoding; }
    @Override public boolean getXmlStandalone() { return standalone; }
    @Override public void setXmlStandalone(boolean s) { standalone = s; }
    @Override public String getXmlVersion() { return version; }
    @Override public void setXmlVersion(String v) { version = v; }
    @Override public boolean getStrictErrorChecking() { return strict; }
    @Override public void setStrictErrorChecking(boolean s) { strict = s; }
    @Override public String getDocumentURI() { return uri; }
    @Override public void setDocumentURI(String u) { uri = u; }
    @Override public Node adoptNode(Node n) {
      if (!(n instanceof NodeImpl) || n instanceof Document || n instanceof DocumentType) return null;
      NodeImpl c = (NodeImpl) n;
      if (c.parent != null) c.parent.removeChild(c);
      if (c instanceof AttrImpl && ((AttrImpl) c).owner != null) ((AttrImpl) c).owner.removeAttributeNode((Attr) c);
      c.setDoc(this);
      c.notifyData(UserDataHandler.NODE_ADOPTED, null);
      return c;
    }
    @Override public DOMConfiguration getDomConfig() { return Config.INSTANCE; }
    @Override public void normalizeDocument() { normalize(); }
    @Override public Node renameNode(Node n, String ns, String qname) {
      if (n instanceof ElementImpl) { ((ElementImpl) n).rename(ns, qname); return n; }
      if (n instanceof AttrImpl) { ((AttrImpl) n).rename(ns, qname); return n; }
      throw err(DOMException.NOT_SUPPORTED_ERR, "cannot rename " + n.getNodeName());
    }
    void checkName(String n) {
      if (!strict) return;
      if (n == null || n.isEmpty() || !(Character.isLetter(n.charAt(0)) || n.charAt(0) == '_' || n.charAt(0) == ':'))
        throw err(DOMException.INVALID_CHARACTER_ERR, "invalid XML name: " + n);
    }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) {
      DocumentImpl d = new DocumentImpl(impl);
      d.uri = uri; d.inputEncoding = inputEncoding; d.xmlEncoding = xmlEncoding; d.version = version; d.standalone = standalone;
      if (deep) for (Node k : kids) d.appendChild(((NodeImpl) k).copy(d, true));
      return d;
    }
  }

  static final class Config implements DOMConfiguration {
    static final Config INSTANCE = new Config();
    final HashMap<String, Object> p = new HashMap<>();
    @Override public void setParameter(String n, Object v) { p.put(n, v); }
    @Override public Object getParameter(String n) { return p.get(n); }
    @Override public boolean canSetParameter(String n, Object v) { return true; }
    @Override public DOMStringList getParameterNames() {
      ArrayList<String> l = new ArrayList<>(p.keySet());
      return new DOMStringList() {
        public String item(int i) { return i < l.size() ? l.get(i) : null; }
        public int getLength() { return l.size(); }
        public boolean contains(String s) { return l.contains(s); }
      };
    }
  }

  public static final class Impl implements DOMImplementation {
    public static final Impl INSTANCE = new Impl();
    @Override public boolean hasFeature(String f, String v) {
      if (f == null) return false;
      if (f.startsWith("+")) f = f.substring(1);
      f = f.toLowerCase(java.util.Locale.ROOT);
      return (f.equals("core") || f.equals("xml")) && (v == null || v.isEmpty() || v.equals("1.0") || v.equals("2.0") || v.equals("3.0"));
    }
    @Override public DocumentType createDocumentType(String qname, String publicId, String systemId) { return new DocTypeImpl(null, qname, publicId, systemId, null); }
    @Override public Document createDocument(String ns, String qname, DocumentType doctype) {
      DocumentImpl d = new DocumentImpl(this);
      if (doctype != null) d.appendChild(doctype);
      if (qname != null) d.appendChild(d.createElementNS(ns, qname));
      return d;
    }
    @Override public Object getFeature(String f, String v) { return hasFeature(f, v) ? this : null; }
  }

  static String localOf(String q) { int c = q.indexOf(':'); return c < 0 ? q : q.substring(c + 1); }
  static String prefixOf(String q) { int c = q.indexOf(':'); return c < 0 ? null : q.substring(0, c); }

  public static final class ElementImpl extends ParentImpl implements Element {
    String ns, name, local, prefix;
    final AttrMap attrs = new AttrMap(this);
    ElementImpl(DocumentImpl d, String ns, String qname) { super(d); rename(ns, qname); }
    void rename(String namespace, String qname) {
      ns = namespace; name = qname;
      if (namespace != null || qname.indexOf(':') >= 0) { local = localOf(qname); prefix = prefixOf(qname); } else { local = null; prefix = null; }
    }
    @Override public String getNodeName() { return name; }
    @Override public short getNodeType() { return ELEMENT_NODE; }
    @Override public String getTagName() { return name; }
    @Override public String getNamespaceURI() { return ns; }
    @Override public String getPrefix() { return prefix; }
    @Override public void setPrefix(String p) { prefix = p; name = p == null || p.isEmpty() ? (local != null ? local : name) : p + ":" + (local != null ? local : localOf(name)); }
    @Override public String getLocalName() { return local; }
    @Override public NamedNodeMap getAttributes() { return attrs; }
    @Override public boolean hasAttributes() { return !attrs.list.isEmpty(); }
    @Override public String getAttribute(String n) { Attr a = getAttributeNode(n); return a == null ? "" : a.getValue(); }
    @Override public void setAttribute(String n, String v) {
      AttrImpl a = (AttrImpl) getAttributeNode(n);
      if (a == null) { doc.checkName(n); a = new AttrImpl(doc, null, n); a.owner = this; attrs.list.add(a); }
      a.value = v;
    }
    @Override public void removeAttribute(String n) { Attr a = getAttributeNode(n); if (a != null) removeAttributeNode(a); }
    @Override public Attr getAttributeNode(String n) { for (AttrImpl a : attrs.list) if (a.name.equals(n)) return a; return null; }
    @Override public Attr setAttributeNode(Attr a) {
      AttrImpl x = (AttrImpl) a;
      if (x.owner != null && x.owner != this) throw err(DOMException.INUSE_ATTRIBUTE_ERR, "attribute in use");
      if (x.doc != doc) throw err(DOMException.WRONG_DOCUMENT_ERR, "attribute from another document");
      Attr old = getAttributeNode(x.name);
      if (old == a) return null;
      if (old != null) removeAttributeNode(old);
      x.owner = this; attrs.list.add(x);
      return old;
    }
    @Override public Attr removeAttributeNode(Attr a) {
      if (!attrs.list.remove(a)) throw err(DOMException.NOT_FOUND_ERR, "not an attribute of this element");
      ((AttrImpl) a).owner = null;
      return a;
    }
    @Override public NodeList getElementsByTagName(String n) { ArrayList<Node> l = new ArrayList<>(); collect(null, n, false, l); return new Children(l); }
    AttrImpl attrNS(String n, String l) {
      String want = n == null || n.isEmpty() ? null : n;
      for (AttrImpl a : attrs.list) if (eq(want, a.ns) && l.equals(a.local != null ? a.local : a.name)) return a;
      return null;
    }
    @Override public String getAttributeNS(String n, String l) { AttrImpl a = attrNS(n, l); return a == null ? "" : a.value; }
    @Override public void setAttributeNS(String n, String q, String v) {
      AttrImpl a = attrNS(n, localOf(q));
      if (a == null) { doc.checkName(q); a = new AttrImpl(doc, n == null || n.isEmpty() ? null : n, q); a.owner = this; attrs.list.add(a); }
      else a.rename(a.ns, q);
      a.value = v;
    }
    @Override public void removeAttributeNS(String n, String l) { AttrImpl a = attrNS(n, l); if (a != null) removeAttributeNode(a); }
    @Override public Attr getAttributeNodeNS(String n, String l) { return attrNS(n, l); }
    @Override public Attr setAttributeNodeNS(Attr a) {
      AttrImpl x = (AttrImpl) a;
      if (x.owner != null && x.owner != this) throw err(DOMException.INUSE_ATTRIBUTE_ERR, "attribute in use");
      AttrImpl old = attrNS(x.ns, x.local != null ? x.local : x.name);
      if (old == a) return null;
      if (old != null) removeAttributeNode(old);
      x.owner = this; attrs.list.add(x);
      return old;
    }
    @Override public NodeList getElementsByTagNameNS(String n, String l) { ArrayList<Node> out = new ArrayList<>(); collect(n, l, true, out); return new Children(out); }
    @Override public boolean hasAttribute(String n) { return getAttributeNode(n) != null; }
    @Override public boolean hasAttributeNS(String n, String l) { return attrNS(n, l) != null; }
    @Override public TypeInfo getSchemaTypeInfo() { return NO_TYPE; }
    @Override public void setIdAttribute(String n, boolean id) { AttrImpl a = (AttrImpl) getAttributeNode(n); if (a == null) throw err(DOMException.NOT_FOUND_ERR, n); a.id = id; }
    @Override public void setIdAttributeNS(String n, String l, boolean id) { AttrImpl a = attrNS(n, l); if (a == null) throw err(DOMException.NOT_FOUND_ERR, l); a.id = id; }
    @Override public void setIdAttributeNode(Attr a, boolean id) { if (((AttrImpl) a).owner != this) throw err(DOMException.NOT_FOUND_ERR, a.getName()); ((AttrImpl) a).id = id; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) {
      ElementImpl e = new ElementImpl(to, ns, name);
      for (AttrImpl a : attrs.list) { AttrImpl c = (AttrImpl) a.copy(to, true); c.owner = e; e.attrs.list.add(c); }
      if (deep) copyKids(e, to);
      return e;
    }
    @Override void setDoc(DocumentImpl d) { super.setDoc(d); for (AttrImpl a : attrs.list) a.doc = d; }
    @Override public String toString() { return "[" + name + ": null]"; }
  }

  static final TypeInfo NO_TYPE = new TypeInfo() {
    public String getTypeName() { return null; }
    public String getTypeNamespace() { return null; }
    public boolean isDerivedFrom(String a, String b, int m) { return false; }
  };

  static final class AttrMap implements NamedNodeMap {
    final ElementImpl owner;
    final ArrayList<AttrImpl> list = new ArrayList<>();
    AttrMap(ElementImpl e) { owner = e; }
    @Override public Node getNamedItem(String n) { return owner.getAttributeNode(n); }
    @Override public Node setNamedItem(Node a) { return owner.setAttributeNode((Attr) a); }
    @Override public Node removeNamedItem(String n) { Attr a = owner.getAttributeNode(n); if (a == null) throw err(DOMException.NOT_FOUND_ERR, n); return owner.removeAttributeNode(a); }
    @Override public Node item(int i) { return i >= 0 && i < list.size() ? list.get(i) : null; }
    @Override public int getLength() { return list.size(); }
    @Override public Node getNamedItemNS(String n, String l) { return owner.attrNS(n, l); }
    @Override public Node setNamedItemNS(Node a) { return owner.setAttributeNodeNS((Attr) a); }
    @Override public Node removeNamedItemNS(String n, String l) { AttrImpl a = owner.attrNS(n, l); if (a == null) throw err(DOMException.NOT_FOUND_ERR, l); return owner.removeAttributeNode(a); }
  }

  public static final class AttrImpl extends NodeImpl implements Attr {
    String ns, name, local, prefix, value = "";
    ElementImpl owner;
    boolean id, specified = true;
    AttrImpl(DocumentImpl d, String ns, String qname) { super(d); rename(ns, qname); }
    void rename(String namespace, String qname) {
      ns = namespace; name = qname;
      if (namespace != null || qname.indexOf(':') >= 0) { local = localOf(qname); prefix = prefixOf(qname); } else { local = null; prefix = null; }
    }
    @Override public String getNodeName() { return name; }
    @Override public short getNodeType() { return ATTRIBUTE_NODE; }
    @Override public String getNodeValue() { return value; }
    @Override public void setNodeValue(String v) { value = v == null ? "" : v; }
    @Override public Node getParentNode() { return null; }
    @Override public Node getPreviousSibling() { return null; }
    @Override public Node getNextSibling() { return null; }
    @Override public NodeList getChildNodes() { ArrayList<Node> l = new ArrayList<>(); l.add(new TextImpl(doc, value)); return new Children(l); }
    @Override public Node getFirstChild() { return getChildNodes().item(0); }
    @Override public Node getLastChild() { return getFirstChild(); }
    @Override public boolean hasChildNodes() { return true; }
    @Override public String getNamespaceURI() { return ns; }
    @Override public String getPrefix() { return prefix; }
    @Override public void setPrefix(String p) { prefix = p; String l = local != null ? local : localOf(name); name = p == null || p.isEmpty() ? l : p + ":" + l; }
    @Override public String getLocalName() { return local; }
    @Override public String getName() { return name; }
    @Override public boolean getSpecified() { return specified; }
    @Override public String getValue() { return value; }
    @Override public void setValue(String v) { setNodeValue(v); }
    @Override public Element getOwnerElement() { return owner; }
    @Override public TypeInfo getSchemaTypeInfo() { return NO_TYPE; }
    @Override public boolean isId() { return id; }
    @Override public String getTextContent() { return value; }
    @Override public void setTextContent(String t) { setNodeValue(t); }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { AttrImpl a = new AttrImpl(to, ns, name); a.value = value; a.id = id; return a; }
    @Override public String toString() { return name + "=\"" + value + "\""; }
  }

  public abstract static class DataImpl extends NodeImpl implements CharacterData {
    String data;
    DataImpl(DocumentImpl d, String data) { super(d); this.data = data == null ? "" : data; }
    @Override public String getNodeValue() { return data; }
    @Override public void setNodeValue(String v) { data = v == null ? "" : v; }
    @Override public String getData() { return data; }
    @Override public void setData(String d) { setNodeValue(d); }
    @Override public int getLength() { return data.length(); }
    void range(int off, int count) { if (off < 0 || off > data.length() || count < 0) throw err(DOMException.INDEX_SIZE_ERR, "offset " + off + ", count " + count); }
    @Override public String substringData(int off, int count) { range(off, count); return data.substring(off, Math.min(data.length(), off + count)); }
    @Override public void appendData(String s) { data += s; }
    @Override public void insertData(int off, String s) { range(off, 0); data = data.substring(0, off) + s + data.substring(off); }
    @Override public void deleteData(int off, int count) { range(off, count); data = data.substring(0, off) + data.substring(Math.min(data.length(), off + count)); }
    @Override public void replaceData(int off, int count, String s) { range(off, count); data = data.substring(0, off) + s + data.substring(Math.min(data.length(), off + count)); }
  }

  public static class TextImpl extends DataImpl implements Text {
    TextImpl(DocumentImpl d, String data) { super(d, data); }
    @Override public String getNodeName() { return "#text"; }
    @Override public short getNodeType() { return TEXT_NODE; }
    @Override public Text splitText(int off) {
      range(off, 0);
      TextImpl t = getNodeType() == CDATA_SECTION_NODE ? new CDataImpl(doc, data.substring(off)) : new TextImpl(doc, data.substring(off));
      data = data.substring(0, off);
      if (parent != null) parent.insertBefore(t, getNextSibling());
      return t;
    }
    @Override public boolean isElementContentWhitespace() { return data.trim().isEmpty(); }
    @Override public String getWholeText() {
      StringBuilder sb = new StringBuilder();
      Node first = this;
      while (first.getPreviousSibling() instanceof Text) first = first.getPreviousSibling();
      for (Node n = first; n instanceof Text; n = n.getNextSibling()) sb.append(((Text) n).getData());
      return sb.toString();
    }
    @Override public Text replaceWholeText(String content) {
      Node first = this;
      while (first.getPreviousSibling() instanceof Text) first = first.getPreviousSibling();
      for (Node n = first; n instanceof Text; ) { Node next = n.getNextSibling(); if (n != this && parent != null) parent.removeChild(n); n = next; }
      data = content == null ? "" : content;
      return this;
    }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { return new TextImpl(to, data); }
  }
  public static final class CDataImpl extends TextImpl implements CDATASection {
    CDataImpl(DocumentImpl d, String data) { super(d, data); }
    @Override public String getNodeName() { return "#cdata-section"; }
    @Override public short getNodeType() { return CDATA_SECTION_NODE; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { return new CDataImpl(to, data); }
  }
  public static final class CommentImpl extends DataImpl implements Comment {
    CommentImpl(DocumentImpl d, String data) { super(d, data); }
    @Override public String getNodeName() { return "#comment"; }
    @Override public short getNodeType() { return COMMENT_NODE; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { return new CommentImpl(to, data); }
  }
  public static final class PIImpl extends NodeImpl implements ProcessingInstruction {
    final String target;
    String data;
    PIImpl(DocumentImpl d, String target, String data) { super(d); this.target = target; this.data = data == null ? "" : data; }
    @Override public String getNodeName() { return target; }
    @Override public short getNodeType() { return PROCESSING_INSTRUCTION_NODE; }
    @Override public String getNodeValue() { return data; }
    @Override public void setNodeValue(String v) { data = v; }
    @Override public String getTarget() { return target; }
    @Override public String getData() { return data; }
    @Override public void setData(String d) { data = d; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { return new PIImpl(to, target, data); }
  }
  public static final class FragmentImpl extends ParentImpl implements DocumentFragment {
    FragmentImpl(DocumentImpl d) { super(d); }
    @Override public String getNodeName() { return "#document-fragment"; }
    @Override public short getNodeType() { return DOCUMENT_FRAGMENT_NODE; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { FragmentImpl f = new FragmentImpl(to); if (deep) copyKids(f, to); return f; }
  }
  public static final class EntityRefImpl extends ParentImpl implements EntityReference {
    final String name;
    EntityRefImpl(DocumentImpl d, String name) { super(d); this.name = name; }
    @Override public String getNodeName() { return name; }
    @Override public short getNodeType() { return ENTITY_REFERENCE_NODE; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { EntityRefImpl e = new EntityRefImpl(to, name); if (deep) copyKids(e, to); return e; }
  }
  public static final class DocTypeImpl extends NodeImpl implements DocumentType {
    final String name, publicId, systemId, internal;
    DocTypeImpl(DocumentImpl d, String name, String publicId, String systemId, String internal) { super(d); this.name = name; this.publicId = publicId; this.systemId = systemId; this.internal = internal; }
    @Override public String getNodeName() { return name; }
    @Override public short getNodeType() { return DOCUMENT_TYPE_NODE; }
    @Override public String getName() { return name; }
    @Override public NamedNodeMap getEntities() { return EMPTY_MAP; }
    @Override public NamedNodeMap getNotations() { return EMPTY_MAP; }
    @Override public String getPublicId() { return publicId; }
    @Override public String getSystemId() { return systemId; }
    @Override public String getInternalSubset() { return internal; }
    @Override NodeImpl copy(DocumentImpl to, boolean deep) { return new DocTypeImpl(to, name, publicId, systemId, internal); }
  }
  static final NamedNodeMap EMPTY_MAP = new NamedNodeMap() {
    public Node getNamedItem(String n) { return null; }
    public Node setNamedItem(Node a) { throw err(DOMException.NO_MODIFICATION_ALLOWED_ERR, "read only"); }
    public Node removeNamedItem(String n) { throw err(DOMException.NOT_FOUND_ERR, n); }
    public Node item(int i) { return null; }
    public int getLength() { return 0; }
    public Node getNamedItemNS(String n, String l) { return null; }
    public Node setNamedItemNS(Node a) { throw err(DOMException.NO_MODIFICATION_ALLOWED_ERR, "read only"); }
    public Node removeNamedItemNS(String n, String l) { throw err(DOMException.NOT_FOUND_ERR, l); }
  };
}
