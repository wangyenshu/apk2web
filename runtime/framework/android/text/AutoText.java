package android.text;

/**
 * Corrections of common misspellings for QWERTY keyboards' auto-text option (a small English dictionary from
 * AOSP's autotext list).
 */
public class AutoText {
  private static final java.util.HashMap<String, String> WORDS = new java.util.HashMap<>();
  static {
    String[] p = {"abouta", "about a", "aboutit", "about it", "abouthe", "about the", "acn", "can", "adn", "and", "aer", "are", "agian", "again",
        "ahve", "have", "allwasy", "always", "alwasy", "always", "amde", "made", "andthe", "and the", "anf", "and", "arent", "aren't",
        "athe", "the", "becuase", "because", "beleive", "believe", "cant", "can't", "certianly", "certainly", "couldnt", "couldn't",
        "didnt", "didn't", "doesnt", "doesn't", "dont", "don't", "eyt", "yet", "fo", "of", "fomr", "from", "frmo", "from", "freind", "friend",
        "hadnt", "hadn't", "hasnt", "hasn't", "havent", "haven't", "hte", "the", "htis", "this", "i", "I", "id", "I'd", "im", "I'm", "isnt", "isn't",
        "itd", "it'd", "itll", "it'll", "ive", "I've", "jsut", "just", "knwo", "know", "liek", "like", "mkae", "make", "nto", "not", "omw", "on my way",
        "peopel", "people", "recieve", "receive", "seperate", "separate", "shoudl", "should", "shouldnt", "shouldn't", "taht", "that",
        "teh", "the", "thier", "their", "thats", "that's", "theyre", "they're", "tihs", "this", "thna", "than", "tje", "the", "tomorow", "tomorrow",
        "untill", "until", "wasnt", "wasn't", "werent", "weren't", "whats", "what's", "wiht", "with", "wont", "won't", "woudl", "would",
        "wouldnt", "wouldn't", "yoru", "your", "youre", "you're", "youve", "you've"};
    for (int i = 0; i < p.length; i += 2) WORDS.put(p[i], p[i + 1]);
  }
  private AutoText() {}
  /** the replacement of src[start, end), or null */
  public static String get(CharSequence src, int start, int end, android.view.View view) {
    return WORDS.get(src.subSequence(start, end).toString().toLowerCase(java.util.Locale.ROOT));
  }
  public static int getSize(android.view.View view) { return WORDS.size(); }
}
