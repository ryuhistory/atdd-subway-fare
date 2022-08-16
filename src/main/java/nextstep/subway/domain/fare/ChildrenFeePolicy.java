package nextstep.subway.domain.fare;

public class ChildrenFeePolicy implements AdditionalFarePolicy {
	private static final double CHILDREN_DISCOUNT_RATE = 0.5;

	private final boolean isChildren;
	private final long fee;

	public ChildrenFeePolicy(boolean isChildren, long fee) {
		this.isChildren = isChildren;
		this.fee = fee;
	}

	@Override
	public long calculateAdditionalFee() {
		if (!isChildren) {
			return 0;
		}
		return toNegative(Math.floor(this.fee * CHILDREN_DISCOUNT_RATE));
	}

	private long toNegative(double amount) {
		return (long)amount * -1;
	}
}
