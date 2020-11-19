import React, { Component } from 'react';
import { connect } from 'dva';
import styles from './TitleBar.less'
import { Menu, Button, Drawer } from 'antd';
import { HomeOutlined, ShareAltOutlined, BookOutlined, CloudServerOutlined, MessageOutlined,SearchOutlined,MenuOutlined } from '@ant-design/icons';


/**
 * 导航栏
 */
const { SubMenu } = Menu;
class TitleBar extends Component {
    state = {
        scroll: false,
        menuVisible:true,//横向菜单是否隐藏
        drawerVisible:false, //侧边抽屉可见性
    }

    componentDidMount(){
        window.addEventListener('scroll', this.bindHandleScroll);
        window.onresize = this.onwindowResize;
        window.onload = this.onwindowResize;
    }

    componentWillUnmount(){
        window.removeEventListener('scroll', this.bindHandleScroll);
    }

    /**
     * 窗口大小发生改变时
     */
    onwindowResize = () =>{
        //获取当前屏幕宽度
        var _height = window.innerWidth;
        if (_height <= 873 && this.state.menuVisible) {//小屏幕，隐藏横向菜单
            this.setState({
                ...this.state,
                menuVisible:false,
            })
        }else if(!this.state.menuVisible && _height > 873){
            this.setState({
                ...this.state,
                menuVisible:true,
            })
        }
    }

    /**
     * 窗口滚动时
     * @param {} e 
     */
    bindHandleScroll = (e) =>{
        var x =  window.scrollY;
        var titleBar = document.getElementById('titleBar');
        if (x !== 0 && !this.state.scroll) {
            titleBar.style.backgroundColor="#5D6F96";
        }else if(x == 0){
            titleBar.style.background = "transparent";
        }
    }

    /**
     * 小屏幕菜单按钮点击时
     */
    phoneMenuButtonClick = () =>{
        this.setState({
            ...this.state,
            drawerVisible: true
        })
    }

    /**
     * 左侧菜单抽屉关闭时
     */
    onDrawerClose = () =>{
        this.setState({
            ...this.state,
            drawerVisible: false
        })
    }

    /**
     * 返回一个横向菜单
     */
    horizontal_Menu = () => {
        return  <Menu className={styles.horizontal_Menu} 
                    selectedKeys={['']} 
                    mode="horizontal"
                    onClick={({key})=>{
                        this.props.dispatch({type:"mainMenu/go",payload: {pathname: key}});
                    }}
                    //forceSubMenuRender={true}
                    triggerSubMenuAction="hover"
                    builtinPlacements={
                        {
                            bottomLeft: 
                            {
                                points: ['tc', 'bc'], // 子菜单的 "上中" 和 对应菜单的title "下中" 对齐。
                                overflow: {
                                  adjustX: 1,
                                  adjustY: 1
                                },
                                offset: [0, 5]
                            }
                        }
                    }
                >
                    <Menu.Item key="/" icon={<HomeOutlined />}>
                        首页
                    </Menu.Item>
                    <Menu.Item key="/tag" icon={<ShareAltOutlined />}>
                        标签
                    </Menu.Item>
                    <SubMenu title="分类" 
                        icon={<BookOutlined />} 
                        key="/category" 
                        onTitleClick={({key})=>{
                            this.props.dispatch({type:"mainMenu/go",payload: {pathname: key}});
                        }}> 
                        <Menu.Item key="/category1">基本技能</Menu.Item>
                        <Menu.Item key="setting:2">前端技术</Menu.Item>
                        <Menu.Item key="setting:3">后端技术</Menu.Item>
                        <Menu.Item key="setting:4">设计模式</Menu.Item>
                        <Menu.Item key="setting:5">技术周边</Menu.Item>
                        <Menu.Item key="setting:6">读书笔记</Menu.Item>
                    </SubMenu>
                    <SubMenu 
                        title="关于" 
                        icon={<CloudServerOutlined />} 
                        key="about"
                        onTitleClick={({key})=>{
                            this.props.dispatch({type:"mainMenu/go",payload: {pathname: key}});
                        }}>
                        <Menu.Item key="setting:7">关于博客</Menu.Item>
                        <Menu.Item key="setting:8">关于作者</Menu.Item>
                        <Menu.Item key="setting:9">留言小本</Menu.Item>
                    </SubMenu>
                    <Menu.Item key="msgBoard" icon={<MessageOutlined />}>
                        网站导航
                    </Menu.Item>
                </Menu>;
    }

    /**
     * 返回一个纵向菜单
     */
    vertical_Menu = () => {
        return  <Menu className={styles.vertical_Menu} 
                    selectedKeys={['']} 
                    mode="inline"
                    onOpenChange={this.onOpenChange}
                >
                    <Menu.Item key="home" icon={<HomeOutlined />}>
                        首页
                    </Menu.Item>
                    <Menu.Item key="/tag" icon={<ShareAltOutlined />}>
                        标签
                    </Menu.Item>
                    <SubMenu title="分类" icon={<BookOutlined />} key="category">
                        <Menu.Item key="setting:1">基本技能</Menu.Item>
                        <Menu.Item key="setting:2">前端技术</Menu.Item>
                        <Menu.Item key="setting:3">后端技术</Menu.Item>
                        <Menu.Item key="setting:4">设计模式</Menu.Item>
                        <Menu.Item key="setting:5">技术周边</Menu.Item>
                        <Menu.Item key="setting:4">读书笔记</Menu.Item>
                    </SubMenu>
                    <SubMenu title="关于" icon={<CloudServerOutlined />} key="about">
                        <Menu.Item key="setting:4">关于博客</Menu.Item>
                        <Menu.Item key="setting:4">关于作者</Menu.Item>
                        <Menu.Item key="setting:4">留言小本</Menu.Item>
                    </SubMenu>
                    <Menu.Item key="msgBoard" icon={<MessageOutlined />}>
                        网站导航
                    </Menu.Item>
                </Menu>;
    }

    /**
     * 渲染函数
     */
    render() {
        return (
            <div className={styles.titleBar} id="titleBar">
                {/* 小屏幕的菜单按钮键 */}
                <Button 
                    icon={<MenuOutlined style={{color:'white'}}/>} className={styles.phoneMenuButton}
                    onClick={this.phoneMenuButtonClick}
                />
                <h1 className={styles.header}>个人博客</h1>
                <Menu className={styles.search} selectedKeys={['']} mode="horizontal">
                    <Menu.Item key="search" icon={<SearchOutlined onClick={(e)=>{
                            alert("点击了")
                        }}/>}>
                    </Menu.Item>
                </Menu>

                {/* 页面足够宽时的菜单栏 */}
                {
                    this.state.menuVisible 
                    ? 
                    this.horizontal_Menu()
                    :
                    ""
                }

                {/* 侧边菜单栏 */}
                <Drawer
                    title={
                        // React这样在行内样式中引入背景图片
                        <div style={{height:'100px',lineHeight:'100px',textAlign:'center',background:`url(${require("../../../../assets/back.jpg")}) center center`}}>
                            <h2 style={{color:'white'}}>个人博客</h2>
                        </div>
                    }
                    placement="left"
                    closable={false}
                    onClose={this.onDrawerClose}
                    visible={this.state.drawerVisible}
                >
                    {this.vertical_Menu()} 
                </Drawer>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return ({

    });
}

export default connect(mapStateToProps)(TitleBar);