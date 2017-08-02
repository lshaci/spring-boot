package com.lshaci.ownermanaged.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 分页查询结果对象
 * 
 * @author root
 *
 * @param <T>	结果对象类型
 */
public class PageList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;
	
	private int pgCt;		// 当前页
	private int pgSz;		// 每页数据条数
	private int end;		// 总页数
	private int total;		// 总数据条数
	private List<T> results;// 结果集
	
	/**
	 * Create PageList Instance
	 * 
	 * @param pgCt		当前页
	 * @param pgSz		每页数据条数
	 * @param total		总数据条数
	 * @param results	结果集
	 */
	public PageList(int pgCt, int pgSz, int total, List<T> results) {
		super();
		this.pgCt = pgCt;
		this.pgSz = pgSz;
		this.total = total;
		this.results = results;
		this.end = (total + pgSz - 1) / pgSz;
	}
	
	public int getPgCt() {
		return pgCt;
	}
	public int getPgSz() {
		return pgSz;
	}
	public int getEnd() {
		return end;
	}
	public int getTotal() {
		return total;
	}
	public List<T> getResults() {
		return results;
	}
	
	@Override
	public String toString() {
		return "PageList [pgCt=" + pgCt + ", pgSz=" + pgSz + ", end=" + end + ", total=" + total
				+ ", results=" + results + "]";
	}
	
	@Override
    public int size() {
        return results.size();
    }
 
    @Override
    public boolean isEmpty() {
        return results.isEmpty();
    }
 
    @Override
    public boolean contains(Object o) {
        return results.contains(o);
    }
 
    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
 
    @Override
    public Object[] toArray() {
        return results.toArray();
    }
 
    @SuppressWarnings("hiding")
	@Override
    public <T> T[] toArray(T[] a) {
        return results.toArray(a);
    }
 
    @Override
    public boolean add(T e) {
        return results.add(e);
    }
 
    @Override
    public boolean remove(Object o) {
        return results.remove(o);
    }
 
    @Override
    public boolean containsAll(Collection<?> c) {
        return results.containsAll(c);
    }
 
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return results.addAll(c);
    }
 
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return results.addAll(index, c);
    }
 
    @Override
    public boolean removeAll(Collection<?> c) {
        return results.removeAll(c);
    }
 
    @Override
    public boolean retainAll(Collection<?> c) {
        return results.retainAll(c);
    }
 
    @Override
    public void clear() {
        results.clear();
    }
 
    @Override
    public T get(int index) {
        return results.get(index);
    }
 
    @Override
    public T set(int index, T element) {
        return results.set(index, element);
    }
 
    @Override
    public void add(int index, T element) {
        results.add(index, element);
    }
 
    @Override
    public T remove(int index) {
        return results.remove(index);
    }
 
    @Override
    public int indexOf(Object o) {
        return results.indexOf(o);
    }
 
    @Override
    public int lastIndexOf(Object o) {
        return results.lastIndexOf(o);
    }
 
    @Override
    public ListIterator<T> listIterator() {
        return results.listIterator();
    }
 
    @Override
    public ListIterator<T> listIterator(int index) {
        return results.listIterator(index);
    }
 
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return results.subList(fromIndex, toIndex);
    }
}
