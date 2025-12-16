package com.enzulode.mapper.signin;

import com.enzulode.api.dto.SignInRequestDto;
import com.enzulode.domain.SignInRequestModel;
import com.enzulode.mapper.IInterlayerMapper;
import org.springframework.stereotype.Component;

@Component
public class PresentationDomainSignInRequestMapper implements IInterlayerMapper<SignInRequestDto, SignInRequestModel> {

  @Override
  public SignInRequestModel toDest(SignInRequestDto src) {
    return new SignInRequestModel(
        src.username(),
        src.password()
    );
  }

  @Override
  public SignInRequestDto toSrc(SignInRequestModel dest) {
    return new SignInRequestDto(
        dest.username(),
        dest.password()
    );
  }
}
