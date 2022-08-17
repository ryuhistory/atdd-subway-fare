package nextstep.member.application.dto;

import nextstep.member.domain.Member;

public class MemberResponse {
	private Long id;
	private String email;
	private Integer age;
	private boolean isTeenager;
	private boolean isChildren;

	public MemberResponse() {
	}

	public MemberResponse(Long id, String email, Integer age, boolean isTeenager, boolean isChildren) {
		this.id = id;
		this.email = email;
		this.age = age;
		this.isTeenager = isTeenager;
		this.isChildren = isChildren;
	}

	public static MemberResponse of(Member member) {
		return new MemberResponse(member.getId(), member.getEmail(), member.getAge(), member.isTeenager(),
			member.isChildren());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Integer getAge() {
		return age;
	}

	public boolean isTeenager() {
		return isTeenager;
	}

	public boolean isChildren() {
		return isChildren;
	}
}