import React from 'react'
import AppBar from 'material-ui/AppBar';
import axios from 'axios';
import {indigo400} from 'material-ui/styles/colors';

Object.defineProperty(exports, "__esModule", {
    value: true
});

import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';

const indigoColor = indigo400;

class MainContainer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {postRegisters: []};
    }

    componentDidMount() {
        axios.get('/postRegister')
            .then(response => {
                const page = response.data;
                this.setState({postRegisters: page.content});
            })
            .catch(error => {
                console.log(error);
            });

    }

    render() {
        return (
            <div>
                <AppBar title="Blog" style={{color: indigoColor}}/>
                <PostRegisterTable postRegisters={this.state.postRegisters}/>
            </div>

        )
    }
}

exports.default = MainContainer;


class PostRegisterTable extends React.Component {
    render() {
        const postRegisters = this.props.postRegisters.map(postRegister =>
            <PostRegisterRow key={postRegister.id.toString()} postRegister={postRegister}/>
        );
        return (
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHeaderColumn>Preview</TableHeaderColumn>
                        <TableHeaderColumn>Likes</TableHeaderColumn>
                        <TableHeaderColumn>Views</TableHeaderColumn>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {postRegisters}
                </TableBody>
            </Table>
        )
    }
}

class PostRegisterRow extends React.Component {
    render() {
        return (
            <TableRow>
                <TableRowColumn>{this.props.postRegister.preview}</TableRowColumn>
                <TableRowColumn>{this.props.postRegister.likes}</TableRowColumn>
                <TableRowColumn>{this.props.postRegister.views}</TableRowColumn>
            </TableRow>
        )
    }
}