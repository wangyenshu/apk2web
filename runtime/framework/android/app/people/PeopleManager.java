// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.people;

public final class PeopleManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public PeopleManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addOrUpdateStatus(java.lang.String conversationId, android.app.people.ConversationStatus status) { a2w.Stubs.hit("android.app.people.PeopleManager.addOrUpdateStatus"); }
  @a2w.NotImplemented public void clearStatus(java.lang.String conversationId, java.lang.String statusId) { a2w.Stubs.hit("android.app.people.PeopleManager.clearStatus"); }
  @a2w.NotImplemented public void clearStatuses(java.lang.String conversationId) { a2w.Stubs.hit("android.app.people.PeopleManager.clearStatuses"); }
  @a2w.NotImplemented public java.util.List<android.app.people.ConversationStatus> getStatuses(java.lang.String conversationId) { a2w.Stubs.hit("android.app.people.PeopleManager.getStatuses"); return null; }
}
