import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails(props) {
    const status = props.cohort.currentStatus.toLowerCase();
    const statusClass = status === 'ongoing' ? styles.statusOngoing : styles.statusOther;

    return (
        <div className={styles.box}>
            <h3 className={statusClass}>
                {props.cohort.cohortCode} - 
                <span> {props.cohort.technology}</span>
            </h3>
            <dl>
                <dt>Started On</dt>
                <dd>{props.cohort.startDate}</dd>
                <dt>Current Status</dt>
                <dd>{props.cohort.currentStatus}</dd>
                <dt>Coach</dt>
                <dd>{props.cohort.coachName}</dd>
                <dt>Trainer</dt>
                <dd>{props.cohort.trainerName}</dd>
            </dl>
        </div>
    );
}

export default CohortDetails;
