package com.iii.twentywork.model.bean;

import java.util.List;

public class CheckPathInfoBean
{
    private boolean isPass;
    private List<FileTreeBean> folders;
    private List<FileTreeBean> folderTree;
    
    public CheckPathInfoBean() {}
    public CheckPathInfoBean(boolean isPass)
    {
        this.isPass = isPass;
    }
    public CheckPathInfoBean(boolean isPass, List<FileTreeBean> folders)
    {
        this.isPass = isPass;
        this.folders = folders;
    }
    public CheckPathInfoBean(boolean isPass, List<FileTreeBean> folders, List<FileTreeBean> folderTree) {
		this.isPass = isPass;
		this.folders = folders;
		this.folderTree = folderTree;
	}
    
    
	public boolean isPass() { return isPass; }
    public void setPass(boolean isPass) { this.isPass = isPass; }
    
    public List<FileTreeBean> getFolders() { return folders; }
    public void setFolders(List<FileTreeBean> folders) { this.folders = folders; }
	
    public List<FileTreeBean> getFolderTree() { return folderTree;	}
	public void setFolderTree(List<FileTreeBean> folderTree) { this.folderTree = folderTree;}
	
	
	@Override
	public String toString() {
		return "CheckPathInfoBean [isPass=" + isPass + ", folders=" + folders + ", folderTree=" + folderTree + "]";
	}
    
	
	
}
