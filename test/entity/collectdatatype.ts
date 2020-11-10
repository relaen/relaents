
import {CollectData} from './collectdata'
import {TriggerEvent} from './triggerevent'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_collectdata_type",'tunnel')
export class CollectdataType extends BaseEntity{
	@Id()
	@Column({
		name:'collectdata_type_id',
		type:'int',
		nullable:false
	})
	collectdataTypeId:number;

	@Column({
		name:'type_name',
		type:'string',
		nullable:true
	})
	typeName:string;

	@Column({
		name:'simple_name',
		type:'string',
		nullable:true
	})
	simpleName:string;

	@Column({
		name:'valid_min',
		type:'double',
		nullable:true
	})
	validMin:number;

	@Column({
		name:'valid_max',
		type:'double',
		nullable:true
	})
	validMax:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'CollectData',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectdataType',lazyFetch:true})
	collectDatas:Array<CollectData>;

	@OneToMany({entity:'TriggerEvent',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'collectdataType',lazyFetch:true})
	triggerEvents:Array<TriggerEvent>;

}