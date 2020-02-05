import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function DinoPopup({show = false, title, handleClose, children}) {
    return (

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    {title}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {children}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="jurassik" onClick={handleClose}>Close</Button>
            </Modal.Footer>
        </Modal>
    )
}
