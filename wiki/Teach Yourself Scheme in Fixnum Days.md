
# Teach Yourself Scheme in Fixnum Days #

## Chapter 2 Data types ##

### 2.1  Simple data types ###

#### 2.1.2  Numbers ####

*   the tip of the iceberg
*   a large and comprehensive suite of arithemetic and trigonometric procedures
*   comprehensive
    *   英[ˌkɒmprɪ'hensɪv]
    *   美[ˌkɑːmprɪ'hensɪv] 　 　
    *   adj. 全面的；广泛的；综合的；理解的
*   suite
    *   英[swiːt]
    *   美[swiːt] 　 　
    *   n. 套房；一套物件；随员；[计]一组程序；[音]组曲
*   trigonometric　
    *   英[ˌtrɪɡənə'metrɪk]
    *   美[ˌtrɪɡənə'metrɪk] 　 　
    *   adj. 三角法的；据三角法的
*   `atan`
    *   arctangent
*   `exp`
    *   natural antilogarithm
*   `sqrt`
    *   square root
*   R5RS
    *   Richard Kelsey, William Clinger, and Jonathan Rees (eds).
    *   Revised̂5 Report on the Algorithmic Language Scheme (“R5RS”), 1998.
    *   <http://www.schemers.org/Documents/Standards/R5RS/HTML/r5rs.html>

#### 2.1.3 Characters ####

*   prefixing the character with `#\`
    *   `#\c`
*   some non-graphic characters
    *   more descriptive names
    *   `#\newline`
    *   `#\tab`
*   the character for space
    *   `#\ `
    *   `#\space`
*   evaluations

         (char? #\c)       ⇒   #t

         (char? 32)        ⇒   #f

         (char? #\;)       ⇒   #t

         (char? #\ )       ⇒   #t

         (eqv? #\ #\space) ⇒   #t

         (eqv? #\ #\ )     ⇒   #t

         (eqv? #\  #\ )    ⇒   #t

         (eqv? #\
         #\newline)        ⇒   #t

         (eqv? #\
          #\newline)       ⇒   #t
*   note that a semicolon character datum does not trigger a comment
*   R5RS/3.2. Disjointness of types
    >   No object satisfies more than one of the following predicates:
    >
    >   *   `boolean?`
    >   *   `symbol?`
    >   *   `char?`
    >   *   `vector?`
    >   *   `procedure?`
    >   *   `pair?`
    >   *   `number?`
    >   *   `string?`
    >   *   `port?`

*   the character data type has its set of comparison predicates
    *   `char=?`
    *   `char<?`
    *   `char<=?`
    *   `char>?`
    *   `char>=?`

*   to make the comparisons case-insensitive
    *   `char-ci=?`
    *   `char-ci<?`
    *   `char-ci<=?`
    *   `char-ci>?`
    *   `char-ci>=?`

*   the case conversion procedures
    *   `char-downcase`
    *   `char-upcase`

#### 2.1.4 Symbols ####

*   self-evaluating
    *   the evaluated result returned
    *   the same as what you typed in
    *   evaluations

            #t      ⇒   #t
            42      ⇒   42
            #\x     ⇒   #\x
            6/22    ⇒   3/11
            0+1i    ⇒   0+1i
            3.      ⇒   3.0
*   symbols don't behave the same way
    *   used by Scheme programs as __identifiers__ for __variables__
    *   evaluate to the value
*   nevertheless
    *   symbols are a simple data type
    *   symbols are legitimate values that Scheme can traffic in
    *   along with characters, numbers, and the rest
*   nevertheless
    *   英[ˌnevəðə'les]
    *   美[ˌnevərðə'les]
    *   adv. 尽管如此；不过；仍然
    *   conj. 然而；不过
*   legitimate
    *   英[lɪ'dʒɪtɪmət]
    *   美[lɪ'dʒɪtɪmət]
    *   adj. 合法的；世袭的；婚生的；正当的；合理的
    *   vt. 使合法；授权；宣布 ... 为合法
*   traffic in
    *   做 ... 交易
    *   做 ... 买卖
*   you should __quote__ the symbol

        (quote love)    ⇒   'love
        'love           ⇒   'love
*   named by a sequence of characters
    *   the only limitation
        *   it shouldn't be mistakable for some other data
*   examples

        this-is-a-symbol
        i18n
        <=>
        $!#*
*   anti-examples

        16
        -i
        #t
        "this-is-a-string"
        (barf)
*   barf
    *   英[bɑːf]
    *   美[bɑːrf]
    *   v. 呕吐
*   the predicate for checking symbolness

        symbol?
*   Scheme symbols are normally case-insensitive
    *   it's __not__ true on __chicken__ and __racket__
    *   both gives `#f` on `(eqv 'Calorie 'calorie)`
*   calorie
    *   英['kæləri]
    *   美['kæləri]
    *   n. 卡；卡路里(热量单位)
    *   =calory.
*   we can use the symbol `love` as a global variable by using the form `define`

        (define love 13)    ⇒
        love                ⇒    13
*   we can use the form `set!` to change the value held by a variable

        (set! love "unconditional")   ⇒
        love                          ⇒   "unconditional"
        'love                         ⇒   'love

### 2.2 Compound data types ###

*   combining values from other data types in structured ways

#### 2.2.1 Strings ####


*   sequences of characters

*   not to be confused with symbols
    *   which are simple data

*   characters in double-quotes
    *   evaluate to themselves

            "Hello, World!" ⇒   "Hello, World!"


*   the procedure `string` takes a bunch of characters
    *   returns the string made from them

            (string #\h #\e #\l #\l #\o)      ⇒   "hello"
            (define greeting "Hello; Hello!") ⇒
            greeting                          ⇒   "Hello; Hello!"

*   the characters in a given string can be individually accessed and modified

        (string-ref "Love" 0) ⇒   #\L

*   new strings can be created by appending other strings

        (string-append "I " "Love " "You")        ⇒   "I Love You"
        (string-append "E " "Pluribus " "Unum")   ⇒   "E Pluribus Unum"

*   E pluribus unum
    *   合众为一（意为团结统一）
    *   From Wikipedia, the free encyclopedia
        >   E pluribus unum (/ˈiː ˈpluːrᵻbəs ˈuːnəm/; Latin: [ˈeː ˈpluːrɪbʊs ˈuːnũː])—Latin for "Out of many, one" (alternatively translated as "One out of many" or "One from many") — is a 13-letter traditional motto of the United States of America and of the city of Monguagá in Brazil, appearing on the Great Seal along with Annuit cœptis (Latin for "She/He/It approves the undertaking") and Novus ordo seclorum (Latin for "New order of the ages"), and adopted by an Act of Congress in 1782. Never codified by law, E Pluribus Unum was considered a de facto motto of the United States until 1956 when the United States Congress passed an act (H. J. Resolution 396), adopting "In God We Trust" as the official motto.
        >
        >   ...In the poem text, color est e pluribus unus describes the blending of colors into one...
        >
        >   <img src="./Great_Seal_of_the_United_States_(obverse).svg.png"/>
        >
        >   E Pluribus Unum included in the Great Seal of the United States, being one of the nation's mottos at the time of the seal's creation

*   blend
    *   英[blend]
    *   美[blend] 　 　
    *   v. 混合
    *   n. 混合物

*   Great Seal of the United States
    *   美国官方大纹章

*   Eye of Providence
    *   All-seeing Eye
    *   全知之眼
    *   上帝之眼

*   Providence
    *   英['prɒvɪdəns]
    *   美['prɑːvɪdəns] 　 　
    *   n. 天意；天命
    *   n. 远见；卓识；先见之明
    *   Providence: 上帝.

*   obverse
    *   英['ɒbvɜːs]
    *   美['ɑːbvɜːrs]
    *   n. （货币或奖章的）正面；较明显的一面；对应面
    *   adj. 正面的；对应面的

*   reverse
    *   英[rɪ'vɜːs]
    *   美[rɪ'vɜːrs]
    *   v. 逆转；倒退；互换；改变
    *   adj. 相反的；反面的；颠倒的
    *   n. 相反；背面；失败；倒档

*   seal
    *   英[siːl]
    *   美[siːl]
    *   n. 封条；印章
    *   n. 海豹
    *   v. 密封；盖印；明确

*   motto
    *   英['mɒtəʊ]
    *   美['mɑːtoʊ]
    *   n. 座右铭；箴言

*   Mongaguá
    *   From Wikipedia, the free encyclopedia
        >   Mongaguá is a municipality in the state of São Paulo in Brazil. It is part of the Metropolitan Region of Baixada Santista.[1] The population is 52,492 (2015 est.) in an area of 141.87 km².[2] The name comes from the Tupi language. Its seal carries the national motto of the United States of America, E pluribus unum.

*   municipality
    *   英[mjuːˌnɪsɪ'pæləti]
    *   美[mjuːˌnɪsɪ'pæləti]
    *   n. 自治市；市当局

*   codify
    *   英['kəʊdɪfaɪ]
    *   美['kɑːdɪfaɪ]
    *   v. 把…编成法典；整理；编纂

*   Annuit cœptis
    *   某人认可我们开始
    *   From Wikipedia, the free encyclopedia
        >   Annuit cœptis (/ˈænjuːɪt ˈsɛptᵻs/; in classical Latin: [ˈannuɪt ˈkoe̯ptiːs]) is one of two mottos on the reverse side of the Great Seal of the United States. (The second motto is Novus ordo seclorum; another motto appears on the obverse (front) side of the Great Seal: E pluribus unum.) Taken from the Latin words annuo (third-person singular present or perfect annuit), "to nod" or "to approve", and coeptum (plural coepta), "commencement, undertaking", it is literally translated, "[he/she/it] favors our undertakings" or "[he/she/it] has favored our undertakings" (annuit could be in either the present or perfect tense).

*   undertaking　
    *   英[ˌʌndə'teɪkɪŋ]
    *   美[ˌʌndər'teɪkɪŋ]
    *   n. 事业；企业；保证；许诺；殡仪业

*   commencement
    *   英[kə'mensmənt]
    *   美[kə'mensmənt]
    *   n. 开始；毕业典礼

*   Novus ordo seclorum
    *   时代新秩序
    *   From Wikipedia, the free encyclopedia
        >   The phrase Novus ordo seclorum (Latin for "New order of the ages"; English pronunciation: /ˈnoʊvəs ˈɔːrdoʊ sɛˈklɔərəm/; Latin pronunciation: [ˈnɔwʊs ˈoːrdoː seːˈkɫoːrũː]) is the second of two mottos that appear on the reverse (or back side) of the Great Seal of the United States. (The first motto is Annuit cœptis, literally translated "[He/she/it] has favored our undertakings".) The Great Seal was first designed in 1782, and has been printed on the back of the United States one-dollar bill since 1935. The phrase Novus ordo seclorum is sometimes mistranslated as "New World Order" by people who believe in a conspiracy behind the design.
        >
        >   <img src="./Great_Seal_of_the_United_States_(reverse).svg.png"/>
        >
        >   Reverse side of the Great Seal of the United States

*   In God We Trust
    *   我们信靠上帝
    *   From Wikipedia, the free encyclopedia
        >   "In God We Trust" is the official motto of the United States. It was adopted as the nation's motto in 1956 as an alternative or replacement to the unofficial motto of E pluribus unum, which was adopted when the Great Seal of the United States was created and adopted in 1782.

*   trust in　
    *   信任

*   you can make a string of a specified length

        (define mystr (make-string 3))  ⇒
        (string-set! mystr 0 #\T)       ⇒
        (string-set! mystr 1 #\O)       ⇒
        (string-set! mystr 2 #\P)       ⇒
        mystr                           ⇒ "TOP"

*   result of calls to `string`, `make-string`, `string-append` are mutable
*   the `string-set!` replaces the character at a given index

        (define str (string #\r #\e #\a #\d)) ⇒
        str                                   ⇒   "read"
        (string-set! str 2 #\e)               ⇒
        str                                   ⇒   "reed"

#### 2.2.2 Vectors ####

*   sequences like strings
    *   elements can be anything
    *   vectors themselves
    *   multidimensional vectors

*   a way to create a vector

        (vector 0 1 2 3 4)    ⇒   '#(0 1 2 3 4)
        #(a b c)              ⇒   '#(a b c)
        '#(x y z)             ⇒   '#(x y z)

*   in analogy with `make-string`
    *   the procedure `make-vector` makes a vector

            (define v (make-vector 5))    ⇒
            v                             ⇒    '#(0 0 0 0 0)

*   the procedures `vector-ref` and `vector-set!` access and modify vector elements
*   the predicate for checking if something is a vector is `vector?`

#### 2.2.3 Dotted pairs and lists ####

*   a __dotted pair__ is a compound value
    *   combining any two arbitrary values
    *   an ordered couple

*   `car`
    *   the first element

*   `cdr`
    *   the second element

*   `cons`
    *   the combining procedure

*   evaluations

        (cons 1 #t)                         ⇒   '(1 . #t)
        (cons 9/21 (cons 1 #t))             ⇒   '(3/7 1 . #t)
        (cons #\x (cons 9/21 (cons 1 #t)))  ⇒   '(#\x 3/7 1 . #t)

*   dotted pairs are not self-evaluating
    *   one must explicitly quote them

*   evaluations

        '(1 . ())                                           ⇒ '(1)
        '(9/21 . (1 . ()))                                  ⇒ '(3/7 1)
        '(#\x . (9/21 . (1 . ())))                          ⇒ '(#\x 3/7 1)
        (equal? '(#\x 3/7 1) '(#\x . (9/21 . (1 . ()))))    ⇒ #t

*   the accessor procedures are `car` and `cdr`

        (define couple '(Romeo . Juliet))   ⇒
        (car couple)                        ⇒   'Romeo
        (cdr couple)                        ⇒   'Juliet

*   the elements of a dotted pair
    *   can be replaced by the mutator procedures `set-car!` and `set-cdr!`
    *   racket中`set-car!`和`set-cdr!`已经不推荐使用

*   evaluations

        (require scheme/mpair)                  ⇒
        (define couple (mcons 'Romeo 'Juliet))  ⇒
        couple                                  ⇒ (mcons 'Romeo 'Juliet)
        (set-mcar! couple 'Jack)                ⇒
        (set-mcdr! couple 'Rose)                ⇒
        couple                                  ⇒ (mcons 'Jack 'Rose)

*   dotted pairs can contain other dotted pairs

        (define nested '((1 . 2) . 3))  ⇒
        nested                          ⇒   '((1 . 2) . 3)
        (car (car nested))              ⇒   1
        (cdr (car nested))              ⇒   2

*   abbreviations for cascaded compositions of the `car` and `cdr` procedures

        (caar nested)   ⇒   1
        (cdar nested)   ⇒   2

*   upto four cascades are guaranteed
    *   `cadr` `cdadr` `cdaddr` 

*   `cdaddar` might be pushing it

*   push
    *   英[pʊʃ]
    *   美[pʊʃ] 　 　
    *   v. 推；按；挤；逼迫；催促
    *   n. 推；奋力；决心

*   nested along the second element
    *   a special notation

            '(1 . (2 . (3 . (4 . 5))))  ⇒   '(1 2 3 4 . 5)

*   a further abbreviation
    *   if the last cdr is a special object called the __empty list__

            '()                           ⇒   '()
            '(1 . (2 . (3 . (4 . ()))))   ⇒   '(1 2 3 4)

*   the special kind of nested dotted pair is called a __list__
*   a procedure called `list`

        (list 1 2 3 4)  ⇒   '(1 2 3 4)

*   we can use quote to specify the list

        '(1 2 3 4)    ⇒   '(1 2 3 4)

*   elements can be accessed by index

        (define list '(1 2 3 4))  ⇒
        list                      ⇒   '(1 2 3 4)
        (list-ref list 2)         ⇒   3
        (list-tail list 2)        ⇒   '(3 4)

*   the predicates `pair?`, `list?` and `null?`
    *   check if their argument is a dotted pair, list, or the empty list

            (pair? '(1 . 2))  ⇒   #t
            (pair? '(1 2))    ⇒   #t
            (pair? '())       ⇒   #f
            (list? '(1 . 2))  ⇒   #f
            (list? '(1 2))    ⇒   #t
            (list? '())       ⇒   #t
            (null? '(1 . 2))  ⇒   #f
            (null? '(1 2))    ⇒   #f
            (null? '())       ⇒   #t

*   由此可以看出`atom?`确实可以基于`pair?`和`null?`定义

        (define atom?
          (lambda (x)
              (and (not (pair? x)) (not (null? x)))))   ⇒
        (atom? 'a)                                      ⇒   #t
        (atom? '())                                     ⇒   #f
        (atom? 22)                                      ⇒   #t
        (atom? 6/9)                                     ⇒   #t
        (atom? '(1 . 2))                                ⇒   #f

#### 2.2.4 Conversions between data types ####

*   many procedures for converting among the data types

*   convert between the character cases using `char-downcase` and `char-upcase`

        (char-downcase #\A) ⇒   #\a
        (char-upcase #\t)   ⇒   #\T

*   characters can be converted into integers using `char->integer`
    *   and vice versa, `integer->char`

            (char->integer #\A) ⇒   65
            (integer->char 32)  ⇒   #\space

*   strings can be converted into the corresponding list of characters

        (string->list "already")  ⇒   '(#\a #\l #\r #\e #\a #\d #\y)

*   other conversion procedures in the same vein
    *   `list->string`
    *   `vector->list`
    *   `list->vector`

*   vein
    *   英[veɪn]
    *   美[veɪn]
    *   n. 静脉；纹理；叶脉；岩脉
    *   vt. 使有脉络；用脉络装饰

*   in the same vein
    *   同样（情趣、口气、心态）的

*   numbers can be converted to strings

        (number->string 13)     ⇒   "13"
        (number->string 13.)    ⇒   "13.0"
        (number->string 13/26)  ⇒   "1/2"
        (number->string 1+0i)   ⇒   "1"
        (number->string 0+1i)   ⇒   "0+1i"

*   and strings to numbers

        (string->number "9/15")             ⇒   3/5
        (string->number "9i")               ⇒   #f
        (string->number "-9i")              ⇒   0-9i
        (string->number "i am a number")    ⇒   #f

*   `string->number` takes an optional second argument, the radix

        (string->number "cafebabe" 16)      ⇒ 3405691582
        (string->number "110" 2)            ⇒ 6

*   symbols can be converted to strings, and vice versa

        (string->symbol "green")    ⇒   'green
        (symbol->string 'brown)     ⇒   "brown"

*   `0xcafebabe = 3405691582`引起了我的注意
    *   还以为是0-9不重复
    *   想写一个scheme程序遍历16进制数
        *   1-8位
        *   仅有字母构成
        *   对应的10进制数位不重复，从0开始
        *   符合发音规则

### 2.3 Other data types ###

*   other data types

*   __procedure__
    *   `display`
    *   `+`
    *   `cons`
*   variables holding the procedure values
*   not visible as are numbers or characters

        display   ⇒     #<procedure:display>
        +         ⇒     #<procedure:+>
        cons      ⇒     #<procedure:cons>

*   __primitive__ procedures
    *   with standard global variables holding them

*   __port__
    *   the conduit through which input and output is performed
    *   usually associated with files and consoles

*   __display__ can take two arguments
    *   one the value to be displayed
    *   the other the output port it should be displayed on
    *   second argument was implicit
        *   the default output port used is the standard output port

*   the procedure-call __(current‑output‑port)__
    *   the current standard output port

            (display "hello, love" (current-output-port))   ⇒  hello, love

*   conduit　
    *   英['kɒndɪt]
    *   美['kɒndʊɪt]
    *   n. 导管；水管；沟渠

### 2.4 S-expressions ###

*   all the data types
*   can be lumped together
*   a single all-encompassing data type called the s-expression
    *   s for symbolic

*   lump
    *   英[lʌmp]
    *   美[lʌmp]
    *   n. 块；团；笨重的人；瘤
    *   v. 使成块；形成团状；归并；(笨重地)移动

*   lump into　
    *   把…归在一起

*   encompass
    *   英[ɪn'kʌmpəs]
    *   美[ɪn'kʌmpəs]
    *   vt. 围绕；包围；包括；完成

*   all-encompassing　
    *   adj. 包含所有的

*   examples

        42
        #\c
        (1 . 2)
        #(a b c)
        "Hello"
        (quote xyz)
        (string‑>number "16")
        (begin (display "Hello, World!") (newline))

## Chapter 3 Forms ##

*   the Scheme example programs provided thus far
    *   are also s-expressions

*   is true of all Scheme programs
    *   programs are data

*   the character datum `#\c` is a program
    *   or a form
    *   the more general term __form__ instead of __program__

*   the form `#\c` evaluates to the value `#\c`
    *   `#\c` is self-evaluating

*   not all s-expressions are self-evaluating
    *   the symbol s-expression `xyz` evalautes to
        *   the value held by the variable `xyz`
    *   the list s-expression `(string->number "16")` evaluates to
        *   the number 16

*   not all s-expressions are valid programs
    *   `(1 . 2)`

*   evaluates a list form
    *   by examing the __head__ of the form
    *   evaluates to a procedure
        *   the procedure is __applied__ to the arguments
    *   is a __special form__
        *   in a manner idiosyncratic to that form
        *   `begin`
            *   subforms to be evaluated in order
        *   `define`
            *   introduces and initializes a variable
        *   `set!`
            *   changes the binding of a variable

*   idiosyncratic
    *   英[ˌɪdiəsɪŋ'krætɪk]
    *   美[ˌɪdiəsɪŋ'krætɪk]
    *   adj. 特质的；与众不同的

### 3.1 Procedures ###

*   primitive Scheme procedures
    *   `cons`
    *   `string->list`

*   users can create their own procedures
    *   the special form __lambda__
    *   the first subform is __the list of parameters__
    *   the remaining subforms constitute __the procedure's body___

*   evaluations

        (lambda (x) (* x 2))        ⇒   #<procedure>
        ((lambda (x) (* x 2)) 5)    ⇒   10

*   we can use a variable to hold the procedure value
    *   instead of
        *   create a replica using lambda each time

*   evaluations

        (define mul2 (lambda (x) (* x 2)))  ⇒
        (mul2 6)                            ⇒   12
        (mul2 7)                            ⇒   14

#### 3.1.1 Procedure parameters ####

*   the parameters of a lambda-procedure
    *   the first subform
        *   the form immediately following the head

*   unary-procedure
    *   the singleton list (x)

*   the symbol x acts as a variable
    *   the variable is said to be __local__ to the procedure's body

*   2-argument procedure example

        (define area (lambda (length breadth) (* length breadth)))  ⇒
        (area 3 4)                                                  ⇒   12
        (define area2 *)                                            ⇒
        (area2 3 4)                                                 ⇒   12
        (area 3 4 5)                                                ⇒   ; area: arity mismatch;
        (area2 3 4 5)                                               ⇒   60

*   area multiples its arguments
    *   so does the primitive procedure `*`

#### 3.1.2 Variable number of arguments ####

*   the lambda parameter list can be
    *   a list of the form `(a ...)`

            (define fn (lambda (a b c x) (length x)))   ⇒
            (fn 'a 'b 'c '(1 2 3))                      ⇒   3
            (fn 'a 'b 'c '(1 2 3 4 5 6 7))              ⇒   7
    *   a symbol `x`

            (define fn (lambda x (length x)))           ⇒
            (fn 1 2 3 4)                                ⇒   4
    *   a dotted pair of the form `(a ... . x)`

            (define fn (lambda (a b c . x) (length x))) ⇒
            (fn 1 2 3 4)                                ⇒   1
            (fn 'a 'b 'c 1 2 3 4 5 6 7)                 ⇒   7

### 3.2 apply ###

*   the Scheme procedure `apply`
    *   call a procedure on a list of its arguments

*   evaluations

        (apply + '(1 2 3 4))    ⇒   10
        (apply + 1 2 '(3 4 5))  ⇒   15

### 3.3 Sequencing ###

*   the __begin__ special form
    *   bunch together a group of subforms
    *   to be evaluated in sequence

*   implicit __begin__s
    *   lambda-bodies are implicit __begin__s

            (define do-in-seq
              (lambda (task1 task2 task3)
                (begin
                  (display task1)
                  (display task2)
                  (display task3)
                  (newline))))              ⇒
            (do-in-seq 'a 'b 'c)            ⇒   abc

            (define do-in-seq
              (lambda (task1 task2 task3)
                (display task1)
                (display task2)
                (display task3)
                (newline)))                 ⇒
            (do-in-seq 'x 'y 'z)            ⇒   xyz

## Chapter 4 Conditionals ##

*   Scheme provides __conditionals__
    *   the basic form is the __if__

            (define bonus 520)          ⇒
            (if (= bonus 520)
              (display "I love you")
              (display "I hate you"))   ⇒   I love you

            (define bonus 521)          ⇒
            (if (= bonus 520)
              (display "I love you")
              (display "I hate you"))   ⇒   I hate you

示例

    (display (if #t 'good 'bad))    ⇒   good
    (display (if #f 'good 'bad))    ⇒   bad
    (display (if #f 'good))         ⇒   ; ... missing an "else" expression
    (display (if '() 'good))        ⇒   ; ... missing an "else" expression
    (display (if #t 'good))         ⇒   ; ... missing an "else" expression

*   文中说else是可选的，但发现是强制的

*   some other conditional forms for convenience
    *   all be defined as macros
        *   expand into if-expressions

### 4.1 when and unless ###

