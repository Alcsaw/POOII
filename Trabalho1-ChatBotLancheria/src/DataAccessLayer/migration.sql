insert into cliente (nome) values ("José Silva");
insert into cliente (nome) values ("Maria Gomes");
insert into cliente (nome) values ("Ana Paula");
insert into cliente (nome) values ("Pedro Lima");
insert into cliente (nome) values ("João Soares");

insert into categoria (descricao) values ("Hamburgueres");
insert into categoria (descricao) values ("Pizzas");
insert into categoria (descricao) values ("Cachorros quentes");
insert into categoria (descricao) values ("Salgados");
insert into categoria (descricao) values ("Bebidas");

insert into produto (categoria_id, descricao, preco) values (1, "Hamburger duplo", 10);
insert into produto (categoria_id, descricao, preco) values (2, "Pizza de frango", 20);
insert into produto (categoria_id, descricao, preco) values (3, "Cachorro quente grande", 30);
insert into produto (categoria_id, descricao, preco) values (4, "Coxinha", 40);
insert into produto (categoria_id, descricao, preco) values (5, "Coca-cola", 50);

insert into pedido (cliente_id, data, finalizado, entregue) values (1, NOW(), 1,1);
insert into pedido (cliente_id, data, finalizado, entregue) values (2, NOW(), 0,0);
insert into pedido (cliente_id, data, finalizado, entregue) values (2, NOW(), 1,0);
insert into pedido (cliente_id, data, finalizado, entregue) values (3, NOW(), 1,1);
insert into pedido (cliente_id, data, finalizado, entregue) values (4, NOW(), 1,1);

insert into pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (1,1,1,10,"obs 1");
insert into pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (2,2,2,20,"obs 2");
insert into pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (3,3,3,30,"obs 3");
insert into pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (4,4,4,40,"obs 4");
insert into pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (5,5,5,50,"obs 5");

