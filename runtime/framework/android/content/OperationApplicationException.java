package android.content;
public class OperationApplicationException extends Exception {
  private final int mNumSuccessfulYieldPoints;
  public OperationApplicationException() { mNumSuccessfulYieldPoints = 0; }
  public OperationApplicationException(String message) { super(message); mNumSuccessfulYieldPoints = 0; }
  public OperationApplicationException(String message, Throwable cause) { super(message, cause); mNumSuccessfulYieldPoints = 0; }
  public OperationApplicationException(Throwable cause) { super(cause); mNumSuccessfulYieldPoints = 0; }
  public OperationApplicationException(int n) { mNumSuccessfulYieldPoints = n; }
  public OperationApplicationException(String message, int n) { super(message); mNumSuccessfulYieldPoints = n; }
  public int getNumSuccessfulYieldPoints() { return mNumSuccessfulYieldPoints; }
}
