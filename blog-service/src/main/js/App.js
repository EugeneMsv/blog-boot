import React from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
//my components
import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import HomePage from "./components/homepage/HomePage";


class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <MuiThemeProvider>
                <div className="App">
                    <Header/>
                    <HomePage/>
                    <Footer/>
                </div>
            </MuiThemeProvider>

        )
    }
}
export default App;

