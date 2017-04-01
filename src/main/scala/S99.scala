/**
  * Created by wanghg on 23/3/2017.
  */
object S99 extends App {

  //  P01 (*) Find the last element of a list.
  //  Example:
  //    scala> last(List(1, 1, 2, 3, 5, 8))
  //  res0: Int = 8

  def last[T](list: List[T]): T = list match {
    case Nil => throw new RuntimeException
    case x :: Nil => x
    case _ :: xs => last(xs)
  }

  assert(8 == last(List(1, 1, 2, 3, 5, 8)))

  //  P02 (*) Find the last but one element of a list.
  //  Example:
  //    scala> penultimate(List(1, 1, 2, 3, 5, 8))
  //  res0: Int = 5

  def penultimate[T](list: List[T]): T = list match {
    case Nil => throw new RuntimeException
    case _ :: Nil => throw new RuntimeException
    case x :: _ :: Nil => x
    case _ :: xs => penultimate(xs)
  }

  assert(5 == penultimate(List(1, 1, 2, 3, 5, 8)))

  //  P03 (*) Find the Kth element of a list.
  //  By convention, the first element in the list is element 0.
  //  Example:
  //
  //    scala> nth(2, List(1, 1, 2, 3, 5, 8))
  //  res0: Int = 2

  def nth[T](n: Int, list: List[T]): T = (n, list) match {
    case (_, Nil) => throw new RuntimeException
    case (0, x :: _) => x
    case (_, _ :: xs) => nth(n - 1, xs)
  }

  assert(2 == nth(2, List(1, 1, 2, 3, 5, 8)))
  assert(3 == nth(3, List(1, 1, 2, 3, 5, 8)))
  assert(5 == nth(4, List(1, 1, 2, 3, 5, 8)))

  //  P04 (*) Find the number of elements of a list.
  //    Example:
  //    scala> length(List(1, 1, 2, 3, 5, 8))
  //  res0: Int = 6

  def length[T](list: List[T], acc: Int): Int = (list, acc) match {
    case (Nil, _) => acc
    case (_ :: xs, _) => length(xs, acc + 1)
  }

  assert(6 == length(List(1, 1, 2, 3, 5, 8), 0))

  //  P05 (*) Reverse a list.
  //  Example:
  //    scala> reverse(List(1, 1, 2, 3, 5, 8))
  //  res0: List[Int] = List(8, 5, 3, 2, 1, 1)

  def reverse[T](list: List[T], acc: List[T]): List[T] = (list, acc) match {
    case (Nil, _) => acc
    case (x :: xs, _) => reverse(xs, x :: acc)
  }

  assert(List(8, 5, 3, 2, 1, 1) == reverse(List(1, 1, 2, 3, 5, 8), Nil))

  //  P06 (*) Find out whether a list is a palindrome.
  //    Example:
  //    scala> isPalindrome(List(1, 2, 3, 2, 1))
  //  res0: Boolean = true

  def isPalindrome[T](list: List[T], init: List[T], fold: List[T]): Boolean = (list, init, fold) match {
    case (Nil, Nil, _) => true
    case (Nil, _ :: _, _) => false
    case (x :: xs, Nil, Nil) =>
      isPalindrome(xs, Nil, x :: Nil) ||
        isPalindrome(xs, x :: Nil, Nil)
    case (_ :: _, Nil, _ :: _) => false
    case (x :: xs, y :: ys, _) =>
      isPalindrome(xs, init, x :: fold) ||
        x == y && isPalindrome(xs, ys, x :: fold) ||
        isPalindrome(xs, x :: init, fold)
  }

  assert(true == isPalindrome(List(1, 2, 3, 2, 1), Nil, Nil))
  assert(false == isPalindrome(List(1, 2, 3, 2, 1, 3), Nil, Nil))
  assert(true == isPalindrome(List(1, 2, 3, 2, 1, 3, 1, 2, 3, 2, 1), Nil, Nil))
  assert(true == isPalindrome(List(1, 2, 3, 3, 2, 1), Nil, Nil))
  assert(true == isPalindrome(List(), Nil, Nil))
  assert(true == isPalindrome(List(1), Nil, Nil))
  assert(true == isPalindrome(List(1, 1), Nil, Nil))
  assert(true == isPalindrome(List(1, 1, 1), Nil, Nil))

  def isPalindrome2: (List[_], List[_], List[_]) => Boolean = {
    case (Nil, Nil, _) => true
    case (Nil, _ :: _, _) => false
    case (x :: xs, Nil, Nil) =>
      isPalindrome2(xs, Nil, x :: Nil) ||
        isPalindrome2(xs, x :: Nil, Nil)
    case (_ :: _, Nil, _ :: _) => false
    case (x :: xs, y :: ys, fold) =>
      isPalindrome2(xs, y :: ys, x :: fold) ||
        x == y && isPalindrome2(xs, ys, x :: fold) ||
        isPalindrome2(xs, x :: y :: ys, fold)
  }

  assert(true == isPalindrome2(List(1, 2, 3, 2, 1), Nil, Nil))
  assert(false == isPalindrome2(List(1, 2, 3, 2, 1, 3), Nil, Nil))
  assert(true == isPalindrome2(List(1, 2, 3, 2, 1, 3, 1, 2, 3, 2, 1), Nil, Nil))
  assert(true == isPalindrome2(List(1, 2, 3, 3, 2, 1), Nil, Nil))
  assert(true == isPalindrome2(List(), Nil, Nil))
  assert(true == isPalindrome2(List(1), Nil, Nil))
  assert(true == isPalindrome2(List(1, 1), Nil, Nil))
  assert(true == isPalindrome2(List(1, 1, 1), Nil, Nil))

  //  P07 (**) Flatten a nested list structure.
  //  Example:
  //    scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  //  res0: List[Any] = List(1, 1, 2, 3, 5, 8)

  def flatten(list: List[_], acc: List[_]): List[_] = (list, acc) match {
    case (Nil, _) => acc
    case ((x: List[_]) :: Nil, _) => flatten(x, acc)
    case (x :: Nil, _) => x :: acc
    case (x :: xs, _) => flatten(x :: Nil, flatten(xs, acc))
  }

  assert(List(1, 1, 2, 3, 5, 8) == flatten(List(List(1, 1), 2, List(3, List(5, 8))), Nil))
  assert(List(1, 1, 2, 3, 5, 8) != flatten(List('x', List(1, 1), 2, List(3, List(5, 8))), Nil))
  assert(List('x', 1, 1, 2, 3, 5, 8) == flatten(List('x', List(1, 1), 2, List(3, List(5, 8))), Nil))

  //  P08 (**) Eliminate consecutive duplicates of list elements.
  //    If a list contains repeated elements they should be replaced with a single copy of the element.
  //    The order of the elements should not be changed.
  //    Example:
  //
  //    scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //  res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

  def compress(list: List[_]): List[_] = list match {
    case x :: y :: rest if x == y => compress(y :: rest)
    case x :: y :: rest if x != y => x :: compress(y :: rest)
    case _ => list
  }

  assert(List('a, 'b, 'c, 'a, 'd, 'e) == compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //  P09 (**) Pack consecutive duplicates of list elements into sublists.
  //    If a list contains repeated elements they should be placed in separate sublists.
  //    Example:
  //
  //    scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //  res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

  def pack(list: List[_], acc: List[List[_]]): List[List[_]] = (list, acc) match {
    case (Nil, _) => acc.reverse
    case (x :: xs, (y :: ys) :: rest) if x == y => pack(xs, (x :: y :: ys) :: rest)
    case (x :: xs, _) => pack(xs, (x :: Nil) :: acc)
  }

  assert(
    List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)) ==
      pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e), Nil))

  //

  println("Done")

}
