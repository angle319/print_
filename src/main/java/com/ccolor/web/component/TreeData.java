package com.ccolor.web.component;

import java.util.LinkedList;
import java.util.List;

public class TreeData<T> {
	
	T data;
	TreeData<T> parent;
    List<TreeData<T>> children;

    public TreeData(T data) {
        this.data = data;
        this.children = new LinkedList<TreeData<T>>();
    }

    public TreeData<T> addChild(T child) {
    	TreeData<T> childNode = new TreeData<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }
    public void removeChild(TreeData<T> t) {
    	TreeData<T> data = t;
    	data.children = new LinkedList<TreeData<T>>();
    }
	
}
