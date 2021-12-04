package ru.project.fitstyle.service.impl.details;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import ru.project.fitstyle.model.entity.user.FitUser;

public class FitUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final Long id;

	private final String username;

	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;

	public FitUserDetails(final Long id, String username, final String password,
                          final Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static FitUserDetails build(final FitUser fitUser) {
		List<GrantedAuthority> authorities = fitUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new FitUserDetails(
				fitUser.getId(),
				fitUser.getEmail(),
				fitUser.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		return Objects.equals(id, ((FitUserDetails) o).id);
	}
}
