import React from 'react';
import { Tooltip } from 'antd';

function NumberTag(props) {
    const style = {
        display:'inline-block',
        marginRight:'8px',
        marginBottom:'8px',
        background:props.background,
        color:'black',
        padding:'4px 1px',
        cursor:'pointer'
    };
        return (
            <Tooltip placement="top" title={props.content}>
                <div style={style}>
                    <span style={{padding:'4px 6px'}}>{props.content}</span>
                    <span style={{border:'rgb(188, 230, 114)',padding:'4px 6px',background:'white'}}>{props.count}</span>
                </div>
            </Tooltip>
        );
}

export default NumberTag;