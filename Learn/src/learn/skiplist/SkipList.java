/**
 * 
 */
package learn.skiplist;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author aman.jha
 * @param <E> the type of elements held in this collection
 *
 */
public class SkipList<E> extends AbstractSequentialList<E> implements List<E>, Cloneable, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9143295596358562701L;

	private static class Node<E>
	{
		E		item;
		Node<E>	up;
		@SuppressWarnings("unused")
		Node<E>	down;
		Node<E>	next;
		Node<E>	prev;

		Node(Node<E> prev, E element, Node<E> next, Node<E> up, Node<E> down)
		{
			this.item = element;
			this.next = next;
			this.down = down;
			this.up = up;
			this.prev = prev;
		}
	}

	transient int			size	= 0;
	transient int			height	= 1;
	transient Node<E>		first;
	transient Node<E>		last;
	transient Node<E>		topMost;
	transient E				minusInf;
	transient Comparator<E>	comparator;

	/**
	 * @param comparator Comparator for Element Types held in the skip list
	 * @param minusInf The Least possible value of the list, that will never be inserted into the list
	 */
	public SkipList(Comparator<E> comparator, E minusInf)
	{
		this.comparator = comparator;
		this.minusInf = minusInf;
		addFirst();
	}

	@Override
	public boolean add(E e)
	{
		return addElement(e);
	}

	LinkedList<E> n = new LinkedList<>();

	private void addFirst()
	{
		Node<E> firstNode = new Node<E>(null, minusInf, null, null, null);
		this.first = firstNode;
	}

	private boolean addElement(E e)
	{
		int insertPos = getInsertPosition(e, first);
		Node<E> prev = node(insertPos);
		Node<E> next = prev.next;
		Node<E> node = new Node<E>(prev, e, next, null, null);
		prev.next = node;
		node.next = next;
		if (next != null)
		{
			next.prev = node;
		}
		Node<E> newNode = null;
		Node<E> start = first;
		while (true)
		{
			if (start.up == null)
			{
				start.up = new Node<E>(null, minusInf, null, null, start);
			}
			start = start.up;

			if (simulateCoinToss() == 1)
			{
				insertPos = getInsertPosition(e, start);
				prev = node(insertPos, start);
				next = prev.next;
				newNode = new Node<E>(prev, e, next, null, node);
				if (next != null)
				{
					next.prev = newNode;
				}
				node.up = newNode;
				newNode.down = node;
			}
			else
				break;
		}
		return true;
	}

	/**
	 * Simulates a coin toss, returns 0 for tails and 1 for heads
	 * @return {@link Integer}
	 */
	private int simulateCoinToss()
	{
		return ThreadLocalRandom.current().nextInt(0, 2);
	}

	/**
	* Returns {@code true} if this list contains the specified element.
	* More formally, returns {@code true} if and only if this list contains
	* at least one element {@code e} such that
	* <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	*
	* @param o element whose presence in this list is to be tested
	* @return {@code true} if this list contains the specified element
	*/
	@Override
	public boolean contains(Object o)
	{
		return indexOf(o) != -1;
	}

	private int getInsertPosition(E e, Node<E> start)
	{
		int index = 0;
		if (e == null)
		{
			return 0;
		}
		for (Node<E> x = start; x != null; x = x.next)
		{
			if (e.equals(x.item) || comparator.compare(e, x.next.item) < 0)
				return index;
			index++;
		}
		return 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the lowest index {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o)
	{
		int index = 0;
		if (o == null)
		{
			for (Node<E> x = first; x != null; x = x.next)
			{
				if (x.item == null)
					return index;
				index++;
			}
		}
		else
		{
			for (Node<E> x = first; x != null; x = x.next)
			{
				if (o.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}

	/**
	 * Returns the (non-null) Node at the specified element index.
	 */
	Node<E> node(int index)
	{
		if (index < (size >> 1))
		{
			Node<E> x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		}
		Node<E> x = last;
		for (int i = size - 1; i > index; i--)
			x = x.prev;
		return x;
	}

	/**
	 * Returns the (non-null) Node at the specified element index.
	 */
	Node<E> node(int index, Node<E> start)
	{
		if (index < (size >> 1))
		{
			Node<E> x = start;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		}
		Node<E> x = last;
		for (int i = size - 1; i > index; i--)
			x = x.prev;
		return x;
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size()
	{
		return this.size;
	}

}
