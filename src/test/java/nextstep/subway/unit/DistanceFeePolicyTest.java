package nextstep.subway.unit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.subway.domain.fare.DistanceFarePolicy;

class DistanceFarePolicyTest {

	@Test
	@DisplayName("거리가 0보다 작을경우")
	void calculateFeeUnderZeroDistance() {
		assertThatThrownBy(() -> new DistanceFarePolicy(0))
			.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> new DistanceFarePolicy(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("기본운임 구간 요금 계산")
	void getBasicFEE() {
		DistanceFarePolicy DistanceFarePolicy1 = new DistanceFarePolicy(1);
		DistanceFarePolicy DistanceFarePolicy2 = new DistanceFarePolicy(9);

		assertThat(DistanceFarePolicy1.calculateFee()).isEqualTo(1250);
		assertThat(DistanceFarePolicy2.calculateFee()).isEqualTo(1250);
	}

	@Test
	@DisplayName("10km 초과 50km 이하 구간인경우")
	void getDivisionOneFEE() {
		DistanceFarePolicy DistanceFarePolicy1 = new DistanceFarePolicy(10);
		DistanceFarePolicy DistanceFarePolicy2 = new DistanceFarePolicy(11);

		assertThat(DistanceFarePolicy1.calculateFee()).isEqualTo(1250);
		assertThat(DistanceFarePolicy2.calculateFee()).isEqualTo(1350);
	}

	@Test
	@DisplayName("50km 초과 구간인경우")
	void getOverDivisionOneFEE() {
		DistanceFarePolicy DistanceFarePolicy1 = new DistanceFarePolicy(50);
		DistanceFarePolicy DistanceFarePolicy2 = new DistanceFarePolicy(51);
		DistanceFarePolicy DistanceFarePolicy3 = new DistanceFarePolicy(58);
		DistanceFarePolicy DistanceFarePolicy4 = new DistanceFarePolicy(59);

		assertThat(DistanceFarePolicy1.calculateFee()).isEqualTo(2050);
		assertThat(DistanceFarePolicy2.calculateFee()).isEqualTo(2150);
		assertThat(DistanceFarePolicy3.calculateFee()).isEqualTo(2150);
		assertThat(DistanceFarePolicy4.calculateFee()).isEqualTo(2250);
	}

}
