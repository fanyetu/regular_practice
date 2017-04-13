这个包着重突出context的作用

    expression ::= command* //表达式，一个表达式包含多条命令
    command ::= loop | primitive //语句命令
    loop ::= 'loopnumber' expression  'end' //循环命令，其中number为自然数
    primitive ::= 'printstring'  | 'space' | 'break' //基本命令，其中string为字符串