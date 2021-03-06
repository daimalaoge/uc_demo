/** kitadmin-v1.0.0-beta1 MIT License By http://kit.zhengjinfan.cn Author Van Zheng */
;
"use strict";
layui.define(["layer", "laytpl", "utils", "lodash", "route"],
		function(t) {
			layui.layer;
			var i = layui.jquery,
					e = layui.laytpl,
					n = layui.utils,
					a = layui.lodash,
					r = n.localStorage,
					o = layui.route,
					d = "KITADMINTABS",
					l = ".kit-tabs",
					s = ".kit-tab-title",
					c = ".kit-tabs-toolsbox",
					h = void 0,
					f = void 0,
					u = void 0,
					p = {
						TITLE: ['<li lay-id="{{d.id}}" data-path={{d.path}}>', '<span title="{{d.title}}">', "{{#if(d.icon){}}", '<i class="layui-icon">{{d.icon}}</i> ', "{{#}}}", "{{d.title}}", "</span>", '<i class="layui-icon kit-tab-close">&#x1006;</i>', "</li>"].join(""),
						CONTENT: ['<div class="kit-tabs-item" data-path={{d.path}} lay-tab-id="{{d.id}}" data-component="{{d.component}}" data-rendered="{{d.rendered}}">', "<router-view></router-view>", "</div>"].join(""),
						TOOLSITEM: ['<li class="kit-item" lay-id="{{d.id}}">', '<a href="javascript:;">', '<span title="{{d.title}}">{{d.title}}</span>&nbsp;', '<i class="layui-icon layui-close">&#x1006;</i>', "</a>", "</li>"].join("")
					},
					v = function() {
						this.config = {
							onChangeBefore: void 0,
							onChanged: void 0,
							onRendered: void 0
						},
								this.version = "1.0.0"
					},
					y = !1;
			v.prototype.set = function(t) {
				return i.extend(!0, this.config, t),
						this
			},
					v.prototype.render = function(t) {
						var e = this,
								a = i(l).attr("kit-target");
						return f = void 0 === f ? i(s) : f,
								h = void 0 === h ? i('div.kit-tabs-content[kit-tabs="' + a + '"]') : h,
								u = void 0 === u ? i(c).children("ul") : u,
								f.children("li").each(function(t, n) {
									var a = i(this),
											r = a.attr("lay-id");
									a.off("click").on("click",
											function() {
												var t = C.getContent(r);
												"false" === t.isRendered && C.renderHTMLForContent(t.elem, t.path),
														e.
														switch (r)
											});
									var o = a.find(".kit-tab-close");
									1 === o.length && o.off("click").on("click",
											function(t) {
												layui.stope(t),
														e.remove(r)
											}),
											i(c).find(".layui-close").off("click").on("click",
													function(t) {
														layui.stope(t);
														var n = i(this).parents(".kit-item").attr("lay-id");
														e.remove(n)
													})
								}),
								C.prevHandler(),
								C.nextHandler(),
								C.toolHandler(),
								C.toolBoxHandler(e),
						y || (y = !0, C.initCaches(e)),
						n.isFunction(t) && t({
							tabCount: i(s).children("li").length,
							isIndex: "#/" === C.findTitle(C.getCurrId()).attr("data-path")
						}),
								e
					},
					v.prototype.add = function(t, i) {
						var a = this;
						a.config;
						if (void 0 === t.active) {
							var r = C.getCaches(),
									o = n.find(r,
											function(i) {
												return i.path === t.path
											});
							if (void 0 !== o && (t.id = o.id), a.exists(t.id)) return void a.
							switch (t.id);
							if (a.existsByPath(t.path)) {
								var d = a.getByPath(t.path);
								return void a.
								switch (d.id)
							}
						} else {
							if (a.exists(t.id)) return void a.
							switch (t.id, !0);
							if (a.existsByPath(t.path)) {
								d = a.getByPath(t.path);
								return void a.
								switch (d.id, !0)
							}
						}
						e(p.TITLE).render(t,
								function(r) {
									f.append(r),
											e(p.CONTENT).render(t,
													function(r) {
														h.append(r),
																e(p.TOOLSITEM).render(t,
																		function(e) {
																			u.append(e);
																			var r = C.getCaches(),
																					o = n.find(r,
																							function(i) {
																								return i.path === t.path
																							});
																			void 0 === o ? r.push({
																				id: t.id,
																				title: t.title,
																				path: t.path,
																				component: t.component,
																				rendered: !1,
																				active: !0,
																				icon: t.icon
																			}) : o.id = t.id,
																					C.setCaches(r),
																					a.render(),
																					void 0 === t.active ? a.
																					switch (t.id) : t.active && a.
																					switch (t.id, !0),
																			n.isFunction(i) && i()
																		})
													})
								})
					},
					v.prototype.remove = function(t) {
						var i = C.findTitle(t);
						if (i.hasClass("layui-this")) {
							var e = i.prev().attr("lay-id");
							this.
							switch (e)
						}
						i.remove(),
								C.findContent(t).remove(),
								C.findTools(t).remove();
						var n = C.getCaches(),
								a = n.findIndex(function(i) {
									return i.id === t
								});
						n.splice(a, 1),
								C.setCaches(n)
					},
					v.prototype.
							switch = function(t) {
						var i = arguments.length > 1 && void 0 !== arguments[1] && arguments[1],
								e = this.config,
								r = C.findTitle(t),
								o = (r.width(), r[0].offsetLeft),
								d = f.parent("div.kit-tab").width();
						if (o > d);
						var l = r.attr("data-path"),
								s = C.getCaches(),
								c = n.find(s,
										function(t) {
											return t.path === l
										});
						if (void 0 !== c && c.active && i && (p(), r.click()), !i) {
							p(),
									a.forEach(s,
											function(t) {
												t.active = !1
											});
							var h = n.find(s,
									function(t) {
										return t.path === l
									});
							void 0 !== h && (h.active = !0),
									C.setCaches(s);
							var u = C.getContent(t);
							"false" === u.isRendered && C.renderHTMLForContent(u.elem, u.path)
						}
						function p() {
							r.addClass("layui-this").siblings().removeClass("layui-this"),
									C.findContent(t).addClass("layui-show").siblings().removeClass("layui-show"),
									C.findTools(t).addClass("layui-this").siblings().removeClass("layui-this");
							var i = C.getContent(t);
							n.setUrlState(i.path, i.path)
						}
						n.isFunction(e.onChanged) && e.onChanged(t)
					},
					v.prototype.switchByPath = function(t) {
						var i = C.findTitleByPath(t);
						i.length > 0 && this.
						switch (i.attr("lay-id"))
					},
					v.prototype.exists = function(t) {
						return C.findTitle(t).length > 0
					},
					v.prototype.existsByPath = function(t) {
						return C.findTitleByPath(t).length > 0
					},
					v.prototype.get = function(t) {
						if (!this.exists(t)) return null;
						var i = C.findTitle(t);
						return {
							id: t,
							title: i.children("span").text(),
							path: i.attr("data-path")
						}
					},
					v.prototype.getByPath = function(t) {
						if (!this.existsByPath(t)) return null;
						var i = C.findTitleByPath(t),
								e = i.children("span").text();
						return {
							id: i.attr("lay-id"),
							title: e,
							path: t
						}
					},
					v.prototype.getCurrId = function() {
						return C.getCurrId()
					},
					v.prototype.init = function() {
						return C.initCaches(this),
								this
					};
			var C = {
				renderHTMLForContent: function(t, i) {
					o.render(i, t,
							function() {
								t.attr("data-rendered", "true")
							})
				},
				prevHandler: function() {
					i(".kit-tabs-prev").off("click").on("click",
							function() {
								var t = f[0].style.left,
										e = parseInt(t.substr(0, t.indexOf("px")));
								0 !== e && (e = (e += i(l).width()) > 0 ? 0 : e, f.animate({
									left: e + "px"
								}))
							})
				},
				nextHandler: function() {
					i(".kit-tabs-next").off("click").on("click",
							function() {
								var t = f[0].style.left,
										e = parseInt(t.substr(0, t.indexOf("px"))),
										n = i(l).width(),
										a = -18;
								if (f.children("li").each(function() {
									var t = i(this).width();
									a += t + 48
								}), n < a && e - n > -a) {
									parseInt(a / n);
									e = e - n >= 0 ? 0 : e - n,
											f.animate({
												left: e + "px"
											})
								}
							})
				},
				toolHandler: function() {
					i(".kit-tabs-tools").off("click").on("click",
							function(t) {
								layui.stope(t),
										i(c).show(),
										i(document).on("click",
												function() {
													i(c).hide(),
															i(this).off("click")
												})
							})
				},
				toolBoxHandler: function(t) {
					var e = this;
					i(c).find("li.kit-item").each(function() {
						var n = i(this),
								a = n.data("action"),
								r = n.attr("lay-id");
						n.off("click").on("click",
								function() {
									var n = e.getCurrId();
									switch (a) {
										case "closeOther":
											f.find('li:not("[lay-id=' + n + ']")').each(function(e, n) {
												var a = i(this).attr("lay-id");
												0 !== e && t.remove(a)
											});
											break;
										case "closeAll":
											f.find("li:first-child").siblings().each(function() {
												var e = i(this).attr("lay-id");
												t.remove(e)
											});
											break;
										default:
											var o = C.getContent(r);
											"false" === o.isRendered && C.renderHTMLForContent(o.elem, o.path),
													t.
													switch (r)
									}
									i(c).hide()
								})
					})
				},
				initCaches: function(t) {
					var i = this.getCaches();
					a.forEach(i,
							function(i) {
								t.add(i)
							})
				},
				findTitle: function(t) {
					return f.children('li[lay-id="' + t + '"]')
				},
				findTitleByPath: function(t) {
					return f.children('li[data-path="' + t + '"]')
				},
				findContent: function(t) {
					return h.children('div.kit-tabs-item[lay-tab-id="' + t + '"]')
				},
				getContent: function(t) {
					var i = this.findContent(t);
					return {
						elem: i,
						component: i.attr("data-component"),
						path: i.attr("data-path"),
						isRendered: i.attr("data-rendered")
					}
				},
				findTools: function(t) {
					return u.find('li.kit-item[lay-id="' + t + '"]')
				},
				getCurrId: function() {
					return f.find("li.layui-this").attr("lay-id")
				},
				getCaches: function() {
					var t = r.getItem(d);
					return null === t && (t = []),
							t
				},
				setCaches: function(t) {
					r.setItem(d, t)
				}
			};
			t("tabs", new v)
		});
//# sourceMappingURL=tabs.js.map
