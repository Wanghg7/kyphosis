
# Scala语言规范 #

## 第3章 类型 ##

### 路径 ###

### 值类型 ###

#### 单体类型 ####

#### 类型投影 ####

#### 类型指示符 ####

#### 参数化类型 ####

#### 元组类型 ####

#### 带标注类型 ####

#### 复合类型 ####

#### 中缀类型 ####

#### 函数类型 ####

#### 存量类型 ####

    Type               ::= InfixType ExistentialClauses
    ExistentialClauses ::= ‘forSome’ ‘{’ ExistentialDcl
                           {semi ExistentialDcl} ‘}’
    ExistentialDcl     ::= ‘type’ TypeDcl
                        |  ‘val’ ValDcl

存量类型的形式为 `T forSome { Q }` ，其中的 `Q` 是一个类型声明序列。

设 `Q` 为 t<sub>1</sub>[tps<sub>1</sub>] >: L<sub>1</sub> <: U<sub>1</sub>，...，t<sub>n</sub>[tps<sub>n</sub>] >: L<sub>n</sub> <: U<sub>n</sub> （其中的类型参数区 [tps<sub>i</sub>] 是可选的）。类型 t<sub>i</sub> 的作用域包括 `T` 和 `Q` 。我们认为类型变量 t<sub>i</sub> 在类型 `T forSome { Q }` 中已获得绑定。我们称未在 `Q` 中声明的类型变量为未绑定的。

`T forSome { Q }` 的一个类型实例写作 `σT` ，其中 `σ` 为对 t<sub>1</sub>，...，t<sub>n</sub> 的一次替换，使得对于所有的 `i` ， σL<sub>i</sub> <: σt<sub>i</sub> <: σU<sub>i</sub>。存量类型 `T forSome { Q }` 的值集是其所有类型实例的值集的合集。

`T forSome { Q }` 的斯柯伦化是一个类型实例 `σT` ，其中 `σ` 为 [t<sup>′</sup><sub>1</sub>/t<sub>1</sub>，...，t<sup>′</sup><sub>n</sub>/t<sub>n</sub>]，而t<sup>′</sup><sub>i</sub>为一个新抽象类型：t<sup>′</sup><sub>i</sub> >: σL<sub>i</sub> <: σU<sub>i</sub>。【即：声明新的抽象类型 t<sup>'</sup><sub>i</sub> 对 `T` 进行实例化，t<sup>'</sup><sub>i</sub>要满足 `Q` 的约束。】

__简化规则__

存量类型遵循下列等价变换：

1.  存量类型中的多个 `forSome` 子句可以合并。例如： `T forSome { Q } forSome { Q' }` 等价于 `T forSome { Q; Q' }` 。
2.  未使用的量词可以删除。例如： `T forSome { Q; Q' }` ，如果 `Q'` 中定义的类型未在T或Q中引用，则等价于 `T forSome { Q }` 。
3.  空的量词可以删除。例如： `T forSome { }` 等价于 `T` 。
4.  对于存量类型 `T forSome { Q }` ，其中 `Q` 包含 `t[tps] >: L <: U` ，如果将 `T` 的 `+t` 替换为 `U` 、 `-t` 替换为 `L` 形成 `T'` ，那么该类型等价于 `T' forSome { Q }` 。

基于值的存在量词

### 非值类型 ###

### 基类型和成员定义 ###

### 类型间关系 ###

### 不稳定类型 ###

### 类型擦除 ###

## 第4章 基本声明和定义 ##

    Dcl         ::=  ‘val’ ValDcl
                  |  ‘var’ VarDcl
                  |  ‘def’ FunDcl
                  |  ‘type’ {nl} TypeDcl
    PatVarDef   ::=  ‘val’ PatDef
                  |  ‘var’ VarDef
    Def         ::=  PatVarDef
                  |  ‘def’ FunDef
                  |  ‘type’ {nl} TypeDef
                  |  TmplDef

__声明__引入名字，并赋予类型。声明出现在类定义和复合类型的改定义中。

__定义__引入名字，用于指称算式和类型。定义出现在对象和类定义中，以及代码块中。声明和定义都会生成绑定，用于建立类型名和类型定义/边界的关联，以及建立算式名和类型的关联。

名字的作用域是包含该绑定的整个语句序列。但是，对于代码块中的提前引用有一个限制：在构成代码块的语句序列 s<sub>1</sub> ... s<sub>n</sub> 中，如果在 s<sub>i</sub> 中引用了在 s<sub>j</sub> 中定义的实体，而且 i ≤ j ，那么对于所有的 i ≤ k ≤ j ：

*   s<sub>k</sub> 不能是一个变量定义。
*   如果 s<sub>k</sub> 是一个值定义，则必须定义为惰性求值。


### 值声明和定义 ###

    Dcl          ::=  ‘val’ ValDcl
    ValDcl       ::=  ids ‘:’ Type
    PatVarDef    ::=  ‘val’ PatDef
    PatDef       ::=  Pattern2 {‘,’ Pattern2} [‘:’ Type] ‘=’ Expr
    ids          ::=  id {‘,’ id}

值声明 `val x: T` 引入名字 `x` ，表示一个类型为 `T` 的值。

值定义 `val x: T = e` 引入名字 `x` ，指称 `e` 的求值结果。如果该值定义不是递归的，则类型 `T` 可以省略，

