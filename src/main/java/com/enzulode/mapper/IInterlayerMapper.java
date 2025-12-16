package com.enzulode.mapper;

public interface IInterlayerMapper<S, D> {
  D toDest(S src);
  S toSrc(D dest);
}
