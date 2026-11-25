// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.textclassifier;

public final class TextClassificationManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public TextClassificationManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.view.textclassifier.TextClassifier createTextClassificationSession(android.view.textclassifier.TextClassificationContext classificationContext) { a2w.Stubs.hit("android.view.textclassifier.TextClassificationManager.createTextClassificationSession"); return null; }
  @a2w.NotImplemented public android.view.textclassifier.TextClassifier getTextClassifier() { a2w.Stubs.hit("android.view.textclassifier.TextClassificationManager.getTextClassifier"); return null; }
  @a2w.NotImplemented public void setTextClassificationSessionFactory(android.view.textclassifier.TextClassificationSessionFactory factory) { a2w.Stubs.hit("android.view.textclassifier.TextClassificationManager.setTextClassificationSessionFactory"); }
  @a2w.NotImplemented public void setTextClassifier(android.view.textclassifier.TextClassifier textClassifier) { a2w.Stubs.hit("android.view.textclassifier.TextClassificationManager.setTextClassifier"); }
}
