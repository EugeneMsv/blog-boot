'use strict';
import React from "react";
import TextField from "material-ui/TextField";

Object.defineProperty(exports, "__esModule", {
    value: true
});

class PostForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            form: {
                title: '',
                author: '',
                text: '',
                code: ''
            }
        };

        /* this.handleChange = this.handleChange.bind(this);
         this.handleSubmit = this.handleSubmit.bind(this);*/
    }

    handleChange(event, propertyName) {
        const form = this.state.form;
        form[propertyName] = event.target.value;
        this.setState({form: form});
    }

    handleSubmit(event) {
        console.log(this.state.form);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={(e) => this.handleSubmit(e)}>
                <TextField floatingLabelText="Название"
                           value={this.state.form.title}
                           onChange={(e) => this.handleChange(e, "title")}/>
                <br/>
                <TextField floatingLabelText="Автор"
                           value={this.state.form.author}
                           onChange={(e) => this.handleChange(e, "author")}/>
                <br/>
                <TextField
                    floatingLabelText="Текст"
                    multiLine={true}
                    rows={2}
                    value={this.state.form.text}
                    fullWidth={true}
                    onChange={(e) => this.handleChange(e, "text")}/>
                <br/>
                <TextField floatingLabelText="Код"
                           value={this.state.form.code}
                           onChange={(e) => this.handleChange(e, "code")}/>
                <input type="submit" value="Сохранить"/>
            </form>
        );
    }
}

export default PostForm;