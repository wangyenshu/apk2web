# org.w3c.dom

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Attr — framework (a2w.xml.dom.Attr); API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 1+]
+ getOwnerElement(): Element  [API 1+]
+ getSchemaTypeInfo(): TypeInfo  [API 8+]
+ getSpecified(): boolean  [API 1+]
+ getValue(): String  [API 1+]
+ isId(): boolean  [API 8+]
+ setValue(String): void  [API 1+]
```

## CDATASection — framework (a2w.xml.dom.CDATASection); API 1+ (0 +, 0 *, 0 ~, 0 -)

## CharacterData — framework (a2w.xml.dom.CharacterData); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ appendData(String): void  [API 1+]
+ deleteData(int, int): void  [API 1+]
+ getData(): String  [API 1+]
+ getLength(): int  [API 1+]
+ insertData(int, String): void  [API 1+]
+ replaceData(int, int, String): void  [API 1+]
+ setData(String): void  [API 1+]
+ substringData(int, int): String  [API 1+]
```

## Comment — framework (a2w.xml.dom.Comment); API 1+ (0 +, 0 *, 0 ~, 0 -)

## DOMConfiguration — framework (a2w.xml.dom.DOMConfiguration); API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ canSetParameter(String, Object): boolean  [API 8+]
+ getParameter(String): Object  [API 8+]
+ getParameterNames(): DOMStringList  [API 8+]
+ setParameter(String, Object): void  [API 8+]
```

## DOMError — declaration; API 8+ (6 +, 0 *, 0 ~, 0 -)

```
+ getLocation(): DOMLocator  [API 8+]
+ getMessage(): String  [API 8+]
+ getRelatedData(): Object  [API 8+]
+ getRelatedException(): Object  [API 8+]
+ getSeverity(): short  [API 8+]
+ getType(): String  [API 8+]
```

## DOMErrorHandler — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ handleError(DOMError): boolean  [API 8+]
```

## DOMException — framework (a2w.xml.dom.DOMException); API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ DOMException(short, String)  [API 1+]
+ code [field]  [API 1+]
```

## DOMImplementation — framework (a2w.xml.dom.DOMImplementation); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ createDocument(String, String, DocumentType): Document  [API 1+]
+ createDocumentType(String, String, String): DocumentType  [API 1+]
+ getFeature(String, String): Object  [API 8+]
+ hasFeature(String, String): boolean  [API 1+]
```

## DOMImplementationList — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ getLength(): int  [API 8+]
+ item(int): DOMImplementation  [API 8+]
```

## DOMImplementationSource — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ getDOMImplementation(String): DOMImplementation  [API 8+]
+ getDOMImplementationList(String): DOMImplementationList  [API 8+]
```

## DOMLocator — declaration; API 8+ (6 +, 0 *, 0 ~, 0 -)

```
+ getByteOffset(): int  [API 8+]
+ getColumnNumber(): int  [API 8+]
+ getLineNumber(): int  [API 8+]
+ getRelatedNode(): Node  [API 8+]
+ getUri(): String  [API 8+]
+ getUtf16Offset(): int  [API 8+]
```

## DOMStringList — framework (a2w.xml.dom.DOMStringList); API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ contains(String): boolean  [API 8+]
+ getLength(): int  [API 8+]
+ item(int): String  [API 8+]
```

## Document — framework (a2w.xml.dom.Document); API 1+ (31 +, 0 *, 0 ~, 0 -)

```
+ adoptNode(Node): Node  [API 8+]
+ createAttribute(String): Attr  [API 1+]
+ createAttributeNS(String, String): Attr  [API 1+]
+ createCDATASection(String): CDATASection  [API 1+]
+ createComment(String): Comment  [API 1+]
+ createDocumentFragment(): DocumentFragment  [API 1+]
+ createElement(String): Element  [API 1+]
+ createElementNS(String, String): Element  [API 1+]
+ createEntityReference(String): EntityReference  [API 1+]
+ createProcessingInstruction(String, String): ProcessingInstruction  [API 1+]
+ createTextNode(String): Text  [API 1+]
+ getDoctype(): DocumentType  [API 1+]
+ getDocumentElement(): Element  [API 1+]
+ getDocumentURI(): String  [API 8+]
+ getDomConfig(): DOMConfiguration  [API 8+]
+ getElementById(String): Element  [API 1+]
+ getElementsByTagName(String): NodeList  [API 1+]
+ getElementsByTagNameNS(String, String): NodeList  [API 1+]
+ getImplementation(): DOMImplementation  [API 1+]
+ getInputEncoding(): String  [API 8+]
+ getStrictErrorChecking(): boolean  [API 8+]
+ getXmlEncoding(): String  [API 8+]
+ getXmlStandalone(): boolean  [API 8+]
+ getXmlVersion(): String  [API 8+]
+ importNode(Node, boolean): Node  [API 1+]
+ normalizeDocument(): void  [API 8+]
+ renameNode(Node, String, String): Node  [API 8+]
+ setDocumentURI(String): void  [API 8+]
+ setStrictErrorChecking(boolean): void  [API 8+]
+ setXmlStandalone(boolean): void  [API 8+]
+ setXmlVersion(String): void  [API 8+]
```

## DocumentFragment — framework (a2w.xml.dom.DocumentFragment); API 1+ (0 +, 0 *, 0 ~, 0 -)

## DocumentType — framework (a2w.xml.dom.DocumentType); API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getEntities(): NamedNodeMap  [API 1+]
+ getInternalSubset(): String  [API 1+]
+ getName(): String  [API 1+]
+ getNotations(): NamedNodeMap  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
```

## Element — framework (a2w.xml.dom.Element); API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ getAttribute(String): String  [API 1+]
+ getAttributeNS(String, String): String  [API 1+]
+ getAttributeNode(String): Attr  [API 1+]
+ getAttributeNodeNS(String, String): Attr  [API 1+]
+ getElementsByTagName(String): NodeList  [API 1+]
+ getElementsByTagNameNS(String, String): NodeList  [API 1+]
+ getSchemaTypeInfo(): TypeInfo  [API 8+]
+ getTagName(): String  [API 1+]
+ hasAttribute(String): boolean  [API 1+]
+ hasAttributeNS(String, String): boolean  [API 1+]
+ removeAttribute(String): void  [API 1+]
+ removeAttributeNS(String, String): void  [API 1+]
+ removeAttributeNode(Attr): Attr  [API 1+]
+ setAttribute(String, String): void  [API 1+]
+ setAttributeNS(String, String, String): void  [API 1+]
+ setAttributeNode(Attr): Attr  [API 1+]
+ setAttributeNodeNS(Attr): Attr  [API 1+]
+ setIdAttribute(String, boolean): void  [API 8+]
+ setIdAttributeNS(String, String, boolean): void  [API 8+]
+ setIdAttributeNode(Attr, boolean): void  [API 8+]
```

## Entity — framework (a2w.xml.dom.Entity); API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getInputEncoding(): String  [API 8+]
+ getNotationName(): String  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
+ getXmlEncoding(): String  [API 8+]
+ getXmlVersion(): String  [API 8+]
```

## EntityReference — framework (a2w.xml.dom.EntityReference); API 1+ (0 +, 0 *, 0 ~, 0 -)

## NameList — declaration; API 8+ (5 +, 0 *, 0 ~, 0 -)

```
+ contains(String): boolean  [API 8+]
+ containsNS(String, String): boolean  [API 8+]
+ getLength(): int  [API 8+]
+ getName(int): String  [API 8+]
+ getNamespaceURI(int): String  [API 8+]
```

## NamedNodeMap — framework (a2w.xml.dom.NamedNodeMap); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ getLength(): int  [API 1+]
+ getNamedItem(String): Node  [API 1+]
+ getNamedItemNS(String, String): Node  [API 1+]
+ item(int): Node  [API 1+]
+ removeNamedItem(String): Node  [API 1+]
+ removeNamedItemNS(String, String): Node  [API 1+]
+ setNamedItem(Node): Node  [API 1+]
+ setNamedItemNS(Node): Node  [API 1+]
```

## Node — framework (a2w.xml.dom.Node); API 1+ (37 +, 0 *, 0 ~, 0 -)

```
+ appendChild(Node): Node  [API 1+]
+ cloneNode(boolean): Node  [API 1+]
+ compareDocumentPosition(Node): short  [API 8+]
+ getAttributes(): NamedNodeMap  [API 1+]
+ getBaseURI(): String  [API 8+]
+ getChildNodes(): NodeList  [API 1+]
+ getFeature(String, String): Object  [API 8+]
+ getFirstChild(): Node  [API 1+]
+ getLastChild(): Node  [API 1+]
+ getLocalName(): String  [API 1+]
+ getNamespaceURI(): String  [API 1+]
+ getNextSibling(): Node  [API 1+]
+ getNodeName(): String  [API 1+]
+ getNodeType(): short  [API 1+]
+ getNodeValue(): String  [API 1+]
+ getOwnerDocument(): Document  [API 1+]
+ getParentNode(): Node  [API 1+]
+ getPrefix(): String  [API 1+]
+ getPreviousSibling(): Node  [API 1+]
+ getTextContent(): String  [API 8+]
+ getUserData(String): Object  [API 8+]
+ hasAttributes(): boolean  [API 1+]
+ hasChildNodes(): boolean  [API 1+]
+ insertBefore(Node, Node): Node  [API 1+]
+ isDefaultNamespace(String): boolean  [API 8+]
+ isEqualNode(Node): boolean  [API 8+]
+ isSameNode(Node): boolean  [API 8+]
+ isSupported(String, String): boolean  [API 1+]
+ lookupNamespaceURI(String): String  [API 8+]
+ lookupPrefix(String): String  [API 8+]
+ normalize(): void  [API 1+]
+ removeChild(Node): Node  [API 1+]
+ replaceChild(Node, Node): Node  [API 1+]
+ setNodeValue(String): void  [API 1+]
+ setPrefix(String): void  [API 1+]
+ setTextContent(String): void  [API 8+]
+ setUserData(String, Object, UserDataHandler): Object  [API 8+]
```

## NodeList — framework (a2w.xml.dom.NodeList); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getLength(): int  [API 1+]
+ item(int): Node  [API 1+]
```

## Notation — framework (a2w.xml.dom.Notation); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
```

## ProcessingInstruction — framework (a2w.xml.dom.ProcessingInstruction); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getData(): String  [API 1+]
+ getTarget(): String  [API 1+]
+ setData(String): void  [API 1+]
```

## Text — framework (a2w.xml.dom.Text); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ getWholeText(): String  [API 8+]
+ isElementContentWhitespace(): boolean  [API 8+]
+ replaceWholeText(String): Text  [API 8+]
+ splitText(int): Text  [API 1+]
```

## TypeInfo — framework (a2w.xml.dom.TypeInfo); API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ getTypeName(): String  [API 8+]
+ getTypeNamespace(): String  [API 8+]
+ isDerivedFrom(String, String, int): boolean  [API 8+]
```

## UserDataHandler — framework (a2w.xml.dom.UserDataHandler); API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ handle(short, String, Object, Node, Node): void  [API 8+]
```

