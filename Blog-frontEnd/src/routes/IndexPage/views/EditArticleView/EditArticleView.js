import React, { Component } from 'react';
import { connect } from 'dva';
import Editor from 'for-editor';

/**
 * 编辑文章页面
 */
class EditArticleView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value:''
        };
    }
    
    /**
     * 用于处理markdown内容发生改变
     * @param {String} val markdown内容
     */
    handleChange = (val) =>{
        this.setState({
            ...this.state,
            value:val
        })
    }

    render() {
        const { value } = this.state;
        return (
            <div>
                <Editor
                    value={value} 
                    onChange={this.handleChange}
                    
                />
            </div>
        );
    }
}

const mapStateToProps = (state)=> {
    return {

    };
}

export default connect(mapStateToProps)(EditArticleView);