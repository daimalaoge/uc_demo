package com.ucomponent.manager.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucomponent.base.ICommons;
import com.ucomponent.manager.controller.vo.Menu;
import com.ucomponent.po.SysMenu;
import com.ucomponent.repository.SysMenuRepository;


@RestController
public class MenuListRest implements ICommons{
  @Autowired
  private SysMenuRepository sysMenuRepository;
  
  @RequestMapping("/index/menulist/")
  public List menulist(HttpServletRequest request){
    List<SysMenu> flist = (ArrayList<SysMenu>)request.getSession().getAttribute(SESSION_MENULIST);
    List<Menu> mlist = new ArrayList<Menu>();
    if(flist == null){
      return null;
    }
    for(SysMenu smenu1:flist){
      if(smenu1.getLevel().equals("1")){
        Menu menu0 = new Menu();
        menu0.setTitle(smenu1.getName());
        menu0.setIcon(smenu1.getIcon());
        menu0.setSpread("true");
        List<Menu> mlist1 = new ArrayList<Menu>();
    
        for(SysMenu smenu2:flist){
          if(smenu2.getLevel().equals("2") && smenu2.getPId() == smenu1.getId()){
            Menu menu = new Menu();
            menu.setTitle(smenu2.getName());
            menu.setIcon(smenu2.getIcon());
            menu.setSpread("true");
            List<Menu> mlist2 = new ArrayList<Menu>();
            for(SysMenu smenu3:flist){
              if(smenu3.getLevel().equals("3") && smenu3.getPId()==smenu2.getId()){
                Menu menu2 = new Menu();
                menu2.setTitle(smenu3.getName());
                menu2.setIcon(smenu3.getIcon());
                menu2.setPath(smenu3.getUrl());
                mlist2.add(menu2);
              }
            }
            menu.setChildren(mlist2);
            mlist.add(menu);
          }
          menu0.setChildren(mlist1);
          mlist.add(menu0);
        }
      }    
    }
    return mlist;
  }
}
