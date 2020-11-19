import React, { Component } from 'react';
import { connect } from 'dva';
import NumberTag from './../../components/NumberTag';
import * as ColorUtils from './../../../../utils/ColorUtil';
import styles from './TagView.less';
import { List,Space, Button,Card  } from 'antd';
import { MessageOutlined, EyeOutlined,FieldTimeOutlined } from '@ant-design/icons';
import { Link,routerRedux } from 'dva/router';

var listData = [];
test()
const IconText = ({ icon, text }) => (
    <Space>
      {React.createElement(icon)}
      {text}
    </Space>
);

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

class TagView extends Component {
    render() {
        return (
            <div className={styles.container}>
                <Card title="标签云" bordered={false} style={{ width: '100%',padding:'0 20%' }}
                  headStyle={{textAlign:'center'}} bodyStyle={{color:'#009688',textAlign:'center'}}
                >
                    <NumberTag content="Spring" count={2} background={ColorUtils.randomColor()}/>
                    <NumberTag content="Mybatis" count={4} background={ColorUtils.randomColor()}/>
                    <NumberTag content="docker" count={13} background={ColorUtils.randomColor()}/>
                    <NumberTag content="Mysql" count={15} background={ColorUtils.randomColor()}/>
                    <NumberTag content="WebSocket" count={2} background={ColorUtils.randomColor()}/>
                    <NumberTag content="Das" count={4} background={ColorUtils.randomColor()}/>
                    <NumberTag content="React" count={13} background={ColorUtils.randomColor()}/>
                    <NumberTag content="WPF" count={15} background={ColorUtils.randomColor()}/>
                    <NumberTag content="WPFRunner2" count={2} background={ColorUtils.randomColor()}/>
                    <NumberTag content="测试" count={4} background={ColorUtils.randomColor()}/>
                </Card>
                <div style={{background:'white',marginTop:'10px'}}>
                    <List
                            itemLayout="vertical"
                            size="large"
                            dataSource={listData}
                            renderItem={item => (
                                <div className={styles.arctleItem}>
                                    <List.Item
                                        //className={styles.arctleItem1}
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
                            </div>
                            )}
                        />
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return ({
  
    });
}

export default connect(mapStateToProps)(TagView);