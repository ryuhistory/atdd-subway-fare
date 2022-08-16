package nextstep.subway.domain.fare;

public class TeenagerFarePolicy implements AdditionalFarePolicy {
	private static final double TEENAGER_DISCOUNT_RATE = 0.2;

	private final boolean isTeenager;
	private final long fee;

	public TeenagerFarePolicy(boolean isTeenager, long fee) {
		this.isTeenager = isTeenager;
		this.fee = fee;
	}

	@Override
	public long calculateAdditionalFee() {
		if (!isTeenager) {
			return 0;
		}
		return toNegative(Math.floor(this.fee * TEENAGER_DISCOUNT_RATE));

	}

	private long toNegative(double amount) {
		return (long)amount * -1;
	}
}
