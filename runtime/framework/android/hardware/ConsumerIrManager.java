// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.hardware;

public final class ConsumerIrManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public ConsumerIrManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.hardware.ConsumerIrManager.CarrierFrequencyRange[] getCarrierFrequencies() { return new android.hardware.ConsumerIrManager.CarrierFrequencyRange[0]; }
  @a2w.Absent public boolean hasIrEmitter() { return false; }
  @a2w.Absent public void transmit(int carrierFrequency, int[] pattern) {  }

    public final class CarrierFrequencyRange {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public CarrierFrequencyRange(a2w.Hidden hidden) { }
    @a2w.NotImplemented public CarrierFrequencyRange(android.hardware.ConsumerIrManager p0, int min, int max) { this((a2w.Hidden) null); a2w.Stubs.hit("android.hardware.ConsumerIrManager.CarrierFrequencyRange.<init>"); }
    @a2w.Absent public int getMaxFrequency() { return 0; }
    @a2w.Absent public int getMinFrequency() { return 0; }
  }
}
