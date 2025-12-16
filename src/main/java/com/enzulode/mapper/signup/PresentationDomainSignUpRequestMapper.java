package com.enzulode.mapper.signup;

import com.enzulode.api.dto.SignUpRequestDto;
import com.enzulode.domain.SignUpRequestModel;
import com.enzulode.mapper.IInterlayerMapper;
import org.springframework.stereotype.Component;

@Component
public class PresentationDomainSignUpRequestMapper implements IInterlayerMapper<SignUpRequestDto, SignUpRequestModel> {

  @Override
  public SignUpRequestModel toDest(SignUpRequestDto src) {
    return new SignUpRequestModel(
        src.username(),
        src.email(),
        src.password()
    );
  }

  @Override
  public SignUpRequestDto toSrc(SignUpRequestModel dest) {
    return new SignUpRequestDto(
        dest.username(),
        dest.email(),
        dest.password()
    );
  }
}
