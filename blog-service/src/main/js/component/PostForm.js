'use strict';
import React from "react";
import TextField from "material-ui/TextField";

Object.defineProperty(exports, "__esModule", {
    value: true
});

class PostForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {title: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({title: event.target.value});
    }

    handleSubmit(event) {
        alert(this.state.title);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <TextField hintText="Title" value={this.state.title} onChange={this.handleChange}/>
                <input type="submit" value="Submit"/>
            </form>
        );
    }
}
exports.default = PostForm;