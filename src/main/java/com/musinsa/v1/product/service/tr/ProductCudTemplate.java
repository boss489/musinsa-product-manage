package com.musinsa.v1.product.service.tr;

public abstract class ProductCudTemplate<T, P> {

	public abstract T preValidate(P p);
	public abstract T process(T t);

	public T execute(P p){
		T t = preValidate(p);
		return process(t);
	}

	public void noReturnExecute(P p){
		T t = preValidate(p);
		process(t);
	}

}
