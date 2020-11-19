import {routerRedux} from 'dva/router'

/**
 * 主菜单模型
 */
export default {
	/**
	 * 命名空间
	 */
	namespace: 'mainMenu',
	
	/**
	 * 状态
	 * @field {string}selectedKey		当前选中菜单项的键值
	 * @field {bool}collapsed			菜单折叠标志
	 */
	state: {
		collapsed: false,
	},

	/**
	 * 订阅
	 */
	subscriptions: {
		setup({dispatch}) {
		},
	},

	effects: {
		/**
		 * 页面跳转
		 * @param {Object} pathname		 目标页面路径
		 */
		*go({payload: {pathname}}, {call, put}) {
			//页面跳转
			yield put(routerRedux.push(pathname));
		},
	},

	reducers: {
	}
};
