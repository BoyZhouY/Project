import React from 'react';
import { connect } from 'dva';
import { Layout } from 'antd';
import { Route, Switch } from 'dva/router';
import HomeView from './views/HomeView';
import TitleBar from './components/TitleBar';
import ArticleView from './views/ArticleView';
import EditArticleView from './views/EditArticleView';
import TagView from './views/TagView';
import { BackTop } from 'antd';
import { UpOutlined } from '@ant-design/icons';
import styles from './IndexPage.less'

const { Content, Footer } = Layout;
function IndexPage(props) {
    const { match } = props;
    window.scrollTo(0,0);
    return (
      <Layout className={styles.layout}>
        {/* 头部固定 */}
        <TitleBar />
        <div className={styles.img} style={{ flex:'0 0 auto',zIndex: 0, width: '100%' }} />
        <Content className={styles.content}>
          <div  style={{ padding: 0, height: 'auto' }}>
            <Switch>
              <Route path={`${match.url}`} exact component={HomeView} />
              <Route path={`/artcle`} exact component={ArticleView} />
              <Route path={`/tag`} exact component={TagView} />
              {/* <Route path={`${match.url}/AdvanceSearch`} exact component={AdvanceSearchView} />
              <Route path={`${match.url}/CSDataOutline/:dataId`} exact component={CSDataOutlineView} />
              <Route path={`${match.url}/CSEventDetail/:dataId`} exact component={CSEventDetailView} />
              <Route path={`${match.url}/CSEventList/:dataId`} exact component={CSEventListView} />
              <Route path={`${match.url}/Feedback`} exact component={FeedbackView} />
              <Route path={`${match.url}/Help`} exact component={HelpView} />
              <Route path={`${match.url}/CSFigure/:dataId`} exact component={CSFigureView} />
              <Route path={`${match.url}/CSStateDetail/:dataId`} exact component={CSStateDetailView} />
              <Route path={`${match.url}/CSStateList/:dataId`} exact component={CSStateListView} />
              <Route path={`${match.url}/DataUpload`} exact component={DataUploadView} />
              <Route path={`${match.url}/DownloadCenter`} exact component={DownloadCenterView} />
              <Route path={`${match.url}/MyCollections`} exact component={MyCollectionsView} />
              <Route path={`${match.url}/MyNotes`} exact component={MyNotesView} />
              <Route path={`${match.url}/QuestionList`} exact component={QuestionListView} />
              <Route path={`${match.url}/SearchResult`} exact component={SearchResultView} />
              <Route path={`${match.url}/CSEventList/:dataId`} exact component={EventListView} />
              <Route path={`${match.url}/CSEventDetail/:dataId`} exact component={CSEventDetailView} /> */}
            </Switch>
          </div>
        </Content>
        <Footer className={styles.footer}>
          <div style={{textAlign:'center'}}>
          Copyright © 2018 - 2020 WENYUAN All Rights Reserved.
          </div>
        </Footer>
        {/* 返回顶部 */}
        <BackTop>
            <div className={styles.BackTop}>
                <UpOutlined />
            </div>
        </BackTop>
      </Layout>
    );
  }

const mapStateToProps = (state) => {
  return ({
    
  });
}

export default connect(mapStateToProps)(IndexPage);
