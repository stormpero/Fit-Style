import logo from './logo.svg';
import './App.css';
import ListUserComponent from './components/ListUserComponent';


function App() {
  return (
    <div>        
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
        </header>
      </div>      
      <div className="container">
          <ListUserComponent />
      </div>
    </div>
  );
}

export default App;
