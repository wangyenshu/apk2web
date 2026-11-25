package a2w.xml.dom;

public interface TypeInfo {
  int DERIVATION_RESTRICTION = 1, DERIVATION_EXTENSION = 2, DERIVATION_UNION = 4, DERIVATION_LIST = 8;
  String getTypeName();
  String getTypeNamespace();
  boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod);
}
