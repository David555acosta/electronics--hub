package com.curso.expecializacion.product.commongMediator;

public interface RequestHandler<T extends Request<T>, R> {
    R handle(T request);

    Class<T> getRequesType();
}
