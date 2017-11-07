package com.yuri.pontointeligente.api.enums;

/*
 * enums são valores pre fixados, que quase nunca são alterados. por esse motivo, é comum deixar
 * os valores fixos dentro da aplicação. cada enum possui um valor cardinal, começando no 0, e incrementa
 * de 1 em 1. nesse caso, o "role" é uma convenção do spring security.
 */
public enum PerfilEnum {
	ROLE_ADMIN, 
	ROLE_USUARIO;
}
