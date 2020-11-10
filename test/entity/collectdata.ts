
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';
import {CollectdataType} from './collectdatatype'
import {TriggerLog} from './triggerlog'

@Entity("t_collect_data",'tunnel')
export class CollectData extends BaseEntity{
	@Id()
	@Column({
		name:'collect_data_id',
		type:'int',
		nullable:false
	})
	collectDataId:number;

	@ManyToOne({entity:CollectData,lazyFetch:true})
	@JoinColumn({name:'collectdata_type_id',refName:'collectdata_type_id'})
	collectdataType:CollectdataType;

	@Column({
		name:'collect_time',
		type:'int',
		nullable:true
	})
	collectTime:number;

	@Column({
		name:'collect_value',
		type:'double',
		nullable:true
	})
	collectValue:number;

	@Column({
		name:'is_error',
		type:'int',
		nullable:true
	})
	isError:number;

	@OneToMany({entity:'TriggerLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectData',lazyFetch:true})
	triggerLogs:Array<TriggerLog>;

}