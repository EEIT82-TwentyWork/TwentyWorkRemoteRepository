package com.iii.twentywork.model.dao.DAOinterface;

import java.util.List;
import java.util.Set;

//import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;

public interface ShareFileDAO
{
    /**
     * 頁面初始化要取得的檔案列表data
     */
    public abstract Set<ShareFileBean> getFileList(int upperFolderId);

    /**
     * 新增單一檔案
     */
    public abstract ShareFileBean insert(ShareFileBean bean); 

    
    /**
     * 取得該team的 Folder樹狀圖資料
     */
//    public abstract List<FileTreeBean> getGroupFolderTree(int teamId);

    
    /**
     * 更新檔案位置或是檔案名稱
     */
//    public abstract ShareFileBean updateFile(ShareFileBean bean, int newFolderId,String newFileName);
    public abstract ShareFileBean updateFile(int fileId, int newFolderId,String newFileName);
    public abstract ShareFileBean updateFileName(int fileId,String newFileName);
    public abstract ShareFileBean updateFileUpperFolder(int fileId,int newUpperFolder);
    
    /**
     * 搜尋檔案,queryString為要搜尋的字串
     */
//    public abstract List<FileTreeBean> findFileByFileName(String queryString,int upperFolderId);
//  public abstract List<ShareFileBean> findFileByFileName(String queryString,int upperFolderId);

    /**
     * 刪除檔案或資料夾
     */
    public abstract int deleteFile(int fileId,boolean isFolder);
    
    /**
     * 用fileId select檔案
     */
    public abstract ShareFileBean selectByFileId(int fileId);
    
    public abstract ShareFileBean getGroupRootBean(int teamId);
   
}
