package com.curso.expecializacion.user.application.query.finByUserName;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindByUserNameHandler implements RequestHandler<FindByUserNameRequest, FindByUserNameResponse> {
    @Override
    public FindByUserNameResponse handle(FindByUserNameRequest request) {
        return null;
    }

    @Override
    public Class<FindByUserNameRequest> getRequesType() {
        return null;
    }
}
