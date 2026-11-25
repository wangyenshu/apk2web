# javax.xml.transform.dom

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DOMLocator — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ getOriginatingNode(): Node  [API 8+]
```

## DOMResult — framework (a2w.xml.transform.dom.DOMResult); API 8+ (11 +, 0 *, 0 ~, 0 -)

```
+ DOMResult()  [API 8+]
+ DOMResult(Node)  [API 8+]
+ DOMResult(Node, Node)  [API 8+]
+ DOMResult(Node, Node, String)  [API 8+]
+ DOMResult(Node, String)  [API 8+]
+ getNextSibling(): Node  [API 8+]
+ getNode(): Node  [API 8+]
+ getSystemId(): String  [API 8+]
+ setNextSibling(Node): void  [API 8+]
+ setNode(Node): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## DOMSource — framework (a2w.xml.transform.dom.DOMSource); API 8+ (7 +, 0 *, 0 ~, 0 -)

```
+ DOMSource()  [API 8+]
+ DOMSource(Node)  [API 8+]
+ DOMSource(Node, String)  [API 8+]
+ getNode(): Node  [API 8+]
+ getSystemId(): String  [API 8+]
+ setNode(Node): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```

