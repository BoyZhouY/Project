import React from 'react';
import styles from './HomeView.less';
import { List,Space, Button,Card,Tabs,Pagination  } from 'antd';
import { MessageOutlined, EyeOutlined,FieldTimeOutlined } from '@ant-design/icons';
import { Link,routerRedux } from 'dva/router';
import NumberTag from '../../components/NumberTag/NumberTag';
import { connect } from 'dva';
import * as ColorUtils from './../../../../utils/ColorUtil';

var listData = [];
test()
const IconText = ({ icon, text }) => (
    <Space>
      {React.createElement(icon)}
      {text}
    </Space>
);
const { TabPane } = Tabs;

function test(){
  for (let i = 0; i < 10; i++) {
    listData.push({
        href: 'https://ant.design',
        title: `教你动手写一个刷课脚本`,
        titleId: i+1,
        artcleType:0,
        content:
          'Docker本身并不是容器，它是创建容器的工具，是应用容器引擎。 K8S，就是基于容器的集群管理平台，它的全称，是kubernetes。',
      });
    }
}

class HomeView extends React.Component {

    state = {
      pageIndex: 1,
    }; 

    onChange = page => {
      this.setState({
        pageIndex: page,
      });
    };

    onClick = ()=> {
      //console.log(item)
        var path = {
          pathname:`/artcle`
        }
        this.props.dispatch(routerRedux.push(path))
    }

    render() {
        return (
          <div>
            
            <div className={styles.container}>
               <div className={styles.left}>
                    <List
                        itemLayout="vertical"
                        size="large"
                        dataSource={listData}
                        renderItem={item => (
                            <div className={styles.arctleItem}>
                              <List.Item
                                className={styles.arctleItem1}
                                key={item.titleId}
                                actions={[
                                    <IconText icon={FieldTimeOutlined} text="2020-01-26 18:13:06" key="editTime" />,
                                    <IconText icon={EyeOutlined} text="156" key="scanCount" />,
                                    <IconText icon={MessageOutlined} text="2" key="message" />,
                                    <Button className={styles.readText} onClick={this.onClick}>阅读全文</Button>
                                ]}
                              >
                                <List.Item.Meta
                                  title={
                                      <Link to={`/home/artcle`} style={{color:'black'}}>
                                        {
                                          item.artcleType === 0 ? 
                                          <Button size="small" style={{background:'#5eb95e',color:'white',borderRadius:'5px',marginRight:'5px'}}>原创</Button>
                                          :
                                          <Button>转载</Button>
                                        }
                                        <h1 style={{display:'inline-block'}}>{item.title}</h1>
                                      </Link>
                                  }
                                />
                              {item.content}
                            </List.Item>
                          </div>)}
                    />
                    {/* 分页参数 */}
                    <Pagination
                        style={{marginRight:'35px',textAlign:'center'}}
                        hideOnSinglePage={true}
                        current={this.state.pageIndex}
                        onChange={this.onChange} 
                        total={500}
                        pageSize={10}
                        showSizeChanger={false}
                        size="small"
                    />
               </div>
               <div className={styles.right}>
                 {/* 博主信息 */}
               <Card title="博主信息" bordered={false} style={{ width: '100%',marginBottom:'30px' }}
                  headStyle={{textAlign:'center'}} bodyStyle={{color:'#009688',textAlign:'center'}}
               >
                  <p>姓名：周泰斗</p>
                  <p>职业：Java程序猿</p>
              </Card>

              {/* 最新文章 */}
              <Tabs defaultActiveKey="['']" style={{ width: '100%',marginBottom:'30px' ,background:'white'}}>
                <TabPane tab="推荐文章" key="1">
                  Content of Tab Pane 1
                </TabPane>
                <TabPane tab="热文排行" key="2">
                  Content of Tab Pane 2
                </TabPane>
              </Tabs>
              <Card title="标签云" bordered={false} style={{ width: '100%' }}
                  headStyle={{textAlign:'center'}} bodyStyle={{color:'#009688',textAlign:'center'}}
               >
                  <NumberTag content="Spring" count={2} background={ColorUtils.randomColor()}/>
                  <NumberTag content="Mybatis" count={4} background={ColorUtils.randomColor()}/>
                  <NumberTag content="docker" count={13} background={ColorUtils.randomColor()}/>
                  <NumberTag content="Mysql" count={15} background={ColorUtils.randomColor()}/>
              </Card>
               </div>
            </div>
          </div>
            
        );
    }
}

const mapStateToProps = (state) => {
  return ({

  });
}

export default connect(mapStateToProps)(HomeView);