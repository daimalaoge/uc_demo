layui.define(['layer', 'utils'], function(exports) {
  var $ = layui.jquery,
    _win = $(window)
  utils = layui.utils,
    localStorage = utils.localStorage;
  var Kit = function() {
    this.config = {
      type: 'TABS'
    };
    this.version = '1.0.0';
  };
  Kit.prototype.set = function(options) {
    var that = this;
    $.extend(true, that.config, options);
    return that;
  };
  Kit.prototype.init = function() {
    _private.tabsInit(this);
    _private.toolsInit();

    // 初始化皮肤
    // _private.themeInit();
  }
  var _private = {
    toolsInit: function() {
      var toggleSide = $('[kit-toggle="side"]');
      toggleSide.on('click', function() {
        var sideBox = $('div[kit-side]');
        var bodyBox = $('div[kit-body]');
        var tabsBox = $('div[kit-tabs-t]');
        var footerBox = $('div[kit-footer]');
        var toggle = toggleSide.attr('data-toggle');
        switch (toggle) {
          case 'on':
            sideBox.animate({ 'width': '0px' });
            bodyBox.animate({ 'left': '0' });
            tabsBox.animate({ 'margin-left': '0' });
            footerBox.animate({ 'left': '0' });
            $(this).attr('data-toggle', 'off');
            toggleSide.find('i.layui-icon').html('&#xe65b;');
            break;
          case 'off':
            sideBox.animate({ 'width': '200px' });
            bodyBox.animate({ 'left': '200px' });
            footerBox.animate({ 'left': '200px' });
            tabsBox.animate({ 'margin-left': '200px' });
            $(this).attr('data-toggle', 'on');
            toggleSide.find('i.layui-icon').html('&#xe65a;');
            break;
        }
      });
      _win.on('resize', function() {
        var toggle = $('[kit-toggle="side"]').attr('data-toggle');
        var width = this.innerWidth;
        if (width < 1024 && toggle === 'on') {
          toggleSide.click();
        }
        if (width > 1024 && toggle === 'off') {
          toggleSide.click();
        }
      });
      _win.resize();
    },
    tabsInit: function(_that) {
      // var layid = new Date().getTime();
      const tpl_title = [
        '<div class="kit-tabs" kit-target="tabs" kit-tabs-t="true">',
        '  <div class="kit-tabs-prev">',
        '    <i class="layui-icon">&#xe65a;</i>',
        '  </div>',
        '  <div class="kit-tab">',
        '    <ul class="kit-tab-title" style="left: 0;">',
        '      <li lay-id="1" class="layui-this" data-path="#/">',
        '        <span title="主页"><i class="layui-icon">&#xe68e;</i> 主页</span>',
        '      </li>',
        '    </ul>',
        '  </div>',
        '  <div class="kit-tabs-next">',
        '    <i class="layui-icon">&#xe65b;</i>',
        '  </div>',
        '  <div class="kit-tabs-tools">',
        '    <i class="layui-icon">&#xe61a;</i>',
        '  </div>',
        '  <div class="kit-tabs-toolsbox layui-anim layui-anim-upbit">',
        '    <ul>',
        '      <li class="kit-item" data-action="refresh">',
        '        <a href="javascript:;" title="刷新当前标签页">',
        '          <span>刷新当前标签页</span>',
        '        </a>',
        '      </li>',
        '      <li class="kit-item-line"></li>',
        '      <li class="kit-item" data-action="closeOther">',
        '        <a href="javascript:;" title="关闭其他标签页">',
        '          <span>关闭其他标签页</span>',
        '        </a>',
        '      </li>',
        '      <li class="kit-item" data-action="closeAll">',
        '        <a href="javascript:;" title="关闭所有标签页">',
        '          <span>关闭所有标签页</span>',
        '        </a>',
        '      </li>',
        '      <li class="kit-item-line"></li>',
        '      <li class="kit-item layui-this" lay-id="1">',
        '        <a href="#/" title="首页">',
        '          <span>首页</span>',
        '        </a>',
        '      </li>',
        '    </ul>',
        '  </div>',
        '</div>',
      ];
      const tpl_content = [
        '<div class="kit-tabs-content" kit-tabs="tabs">',
        '  <div class="kit-tabs-item layui-show" data-rendered="false" data-path="#/" lay-tab-id="1">',
        '    <router-view></router-view>',
        '  </div>',
        '</div>',
      ];

      const { type } = _that.config;
      if (type.toUpperCase() === 'TABS') {
        var _layout = $('.layui-layout-admin');
        _layout.append(tpl_title.join(''));
        var _body = _layout.find('.layui-body');
        _body.html(tpl_content.join('')).css('top', '90px');
      }
    },
    themeInit: function() {
      var key = 'KITADMIN_SETTING_THEME';
      var setting = localStorage.getItem(key);
      if (setting !== null) {
        if (layui.utils.isString(setting)) {
          setting = JSON.parse(setting);
        }
        var themeName = setting.theme;
        var str = $('#theme').attr('href');
        var _themeUrl = str.substr(0, str.lastIndexOf('/') + 1);
        $('#theme').attr('href', _themeUrl + themeName + '.css');
        var _body = $('body');
        if (!_body.hasClass('kit-theme-' + themeName)) {
          _body.addClass('kit-theme-' + themeName);
        }
      }
    }
  };

  var kit = new Kit();
  //kit.init();

  //输出kit接口
  exports('kit', kit);
});