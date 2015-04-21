// modal confirm dialog
var ModalConfirmDialog = React.createClass({
	getInitialState: function() {
		return {
			visible: false,
			title: null,
			messages: null,
			callback: null
		};
	},
	render: function() {
		if (!this.state.visible) {
			return null;
		}
		
		var messages = this.state.messages;
		if (!this.state.messages.length) {
			messages = [this.state.messages];
		}
		return (<Modal bsStyle="danger" title={this.state.title} onRequestHide={this.hide} backdrop="static">
					<div className="modal-body">
						{messages.map(function(element) {
							return <h5>{element}</h5>;
						})}
					</div>
					<div className="modal-footer">
						<Button bsStyle="danger" onClick={this.callback}>OK</Button>
						<Button onClick={this.hide}>Cancel</Button>
					</div>
		</Modal>);
	},
	callback: function() {
		if (this.state.callback) {
			if (this.state.callbackParameters !== undefined) {
				this.state.callback(this.state.callbackParameters);
			} else {
				this.state.callback();
			}
		}
	},
	show: function(title, messages, confirmCallback, callbackParameters) {
		var state = {
			visible: true,
			title: title,
			messages: messages,
			callback: confirmCallback,
		};
		if (callbackParameters !== undefined) {
			state.callbackParameters = callbackParameters;
		}
		this.setState(state);
	},
	hide: function() {
		this.setState({visible: false});
	}
});
