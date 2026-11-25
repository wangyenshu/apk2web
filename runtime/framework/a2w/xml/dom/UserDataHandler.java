package a2w.xml.dom;

public interface UserDataHandler {
  short NODE_CLONED = 1, NODE_IMPORTED = 2, NODE_DELETED = 3, NODE_RENAMED = 4, NODE_ADOPTED = 5;
  void handle(short operation, String key, Object data, Node src, Node dst);
}
