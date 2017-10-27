const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {postRegisters: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/postRegister'}).done(response => {
            this.setState({postRegisters: response.entity.content});
        });
    }

    render() {
        return (
            <PostRegisterList postRegisters={this.state.postRegisters}/>
        )
    }
}

class PostRegisterList extends React.Component {
    render() {
        const postRegisters = this.props.postRegisters.map(postRegister =>
            <PostRegister key={postRegister.id.toString()} postRegister={postRegister}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Preview</th>
                    <th>Likes</th>
                    <th>Views</th>
                    <th>Comments</th>
                    <th>Created</th>
                </tr>
                {postRegisters}
                </tbody>
            </table>
        )
    }
}

class PostRegister extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.postRegister.preview}</td>
                <td>{this.props.postRegister.likes}</td>
                <td>{this.props.postRegister.views}</td>
                <td>{this.props.postRegister.commentsNum}</td>
                <td>{this.props.postRegister.createdTime}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);