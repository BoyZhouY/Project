import React from 'react';
import { connect } from 'dva';
import styles from './ArticleView.less';
import ReactMarkdown from 'react-markdown';
import apiMd from './../../../../md/CONTRIBUTING.md';
import CodeBlock from 'Utils/CodeBlock';
import { MenuOutlined } from '@ant-design/icons';


class ArticleView extends React.Component {

    state={
        contentsVisible:'block',
        markdown:''

    }

    onClick = () =>{
        if(this.state.contentsVisible == 'block'){
            this.setState({
                contentsVisible: 'none',
            });
        }else{
            this.setState({
                contentsVisible: 'block',
            });
        }
        
    }
    UNSAFE_componentWillMount() {
        fetch(apiMd)
            .then(res => res.text())
            .then(text => this.setState({markdown: text}));
    }

    render() {
        return (
            <div className={styles.container}>
                <ReactMarkdown  
                    className={styles.arctle}
                    source={this.state.markdown}
                    escapeHtml={false}
                    renderers={{
                        code: CodeBlock,
                    }}
                   
                />
                <div className={styles.contents} >
                    我是目录
                </div>

                {/* 目录按钮 */}
                <div className={styles.ContentsButton} onAuxClick>
                    <MenuOutlined  />
                </div>
            </div>
        );
    }
}

const mapStateToProps = state =>{
    return {

    };
}

export default connect(mapStateToProps)(ArticleView);