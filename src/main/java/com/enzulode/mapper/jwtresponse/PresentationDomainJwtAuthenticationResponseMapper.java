package com.enzulode.mapper.jwtresponse;

import com.enzulode.api.dto.JwtAuthenticationResponseDto;
import com.enzulode.domain.JwtAuthenticationResponseModel;
import com.enzulode.mapper.IInterlayerMapper;
import org.springframework.stereotype.Component;

@Component
public class PresentationDomainJwtAuthenticationResponseMapper implements IInterlayerMapper<JwtAuthenticationResponseDto, JwtAuthenticationResponseModel> {

  @Override
  public JwtAuthenticationResponseModel toDest(JwtAuthenticationResponseDto src) {
    return new JwtAuthenticationResponseModel(src.token());
  }

  @Override
  public JwtAuthenticationResponseDto toSrc(JwtAuthenticationResponseModel dest) {
    return new JwtAuthenticationResponseDto(dest.token());
  }
}
