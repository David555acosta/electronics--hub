package com.curso.expecializacion.product.common.mediator;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Component

public class Mediator {

    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequesType, Function.identity()));
    }

    //El tipo del metodo es de un tipo t que extienda de request con un valor de tipo R
    // el valor ingresado por parametro es del tipo de la funcion que extiende
    //

    public <R, T extends Request<R>> R dispacth(T request) {

        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler for request " + request.getClass());
        }

        return handler.handle(request);
    }

    @Async
    public <R, T extends Request<R>> void dispacthAsync(T request) {
        this.dispacth(request);
        
    }
}
